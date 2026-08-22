package com.sitoula.internship.config;

import com.sitoula.internship.security.CustomUserDetailsService;
import com.sitoula.internship.security.JwtAuthenticationEntryPoint;
import com.sitoula.internship.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Public
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/swagger-ui/**", "/api-docs/**", "/swagger-ui.html").permitAll()

                        // Internships: GET open to all authenticated roles, write restricted to COMPANY
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/internships/**")
                        .hasAnyRole("STUDENT", "COMPANY", "ADMIN")
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/internships/**").hasRole("COMPANY")
                        .requestMatchers(org.springframework.http.HttpMethod.PUT, "/api/internships/**").hasRole("COMPANY")
                        .requestMatchers(org.springframework.http.HttpMethod.DELETE, "/api/internships/**").hasRole("COMPANY")

                        // Role-restricted modules
                        .requestMatchers("/api/student/**").hasRole("STUDENT")
                        .requestMatchers("/api/company/**").hasRole("COMPANY")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Applications: student applies/tracks, company/admin manage status
                        .requestMatchers("/api/applications/apply", "/api/applications/my-applications").hasRole("STUDENT")
                        .requestMatchers("/api/applications/internship/**").hasAnyRole("COMPANY", "ADMIN")
                        .requestMatchers("/api/applications/*/status").hasAnyRole("COMPANY", "ADMIN")

                        .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
