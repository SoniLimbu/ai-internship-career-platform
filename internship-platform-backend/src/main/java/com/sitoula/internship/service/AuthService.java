package com.sitoula.internship.service;

import com.sitoula.internship.dto.request.LoginRequest;
import com.sitoula.internship.dto.request.RegisterRequest;
import com.sitoula.internship.dto.response.AuthResponse;
import com.sitoula.internship.entity.CompanyProfile;
import com.sitoula.internship.entity.Role;
import com.sitoula.internship.entity.StudentProfile;
import com.sitoula.internship.entity.User;
import com.sitoula.internship.exception.DuplicateResourceException;
import com.sitoula.internship.repository.CompanyProfileRepository;
import com.sitoula.internship.repository.StudentProfileRepository;
import com.sitoula.internship.repository.UserRepository;
import com.sitoula.internship.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final CompanyProfileRepository companyProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username is already taken: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email is already registered: " + request.getEmail());
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        user = userRepository.save(user);

        if (request.getRole() == Role.STUDENT) {
            StudentProfile profile = StudentProfile.builder().user(user).build();
            studentProfileRepository.save(profile);
        } else if (request.getRole() == Role.COMPANY) {
            CompanyProfile profile = CompanyProfile.builder()
                    .user(user)
                    .companyName(request.getCompanyName() != null ? request.getCompanyName() : request.getUsername())
                    .isVerified(false)
                    .build();
            companyProfileRepository.save(profile);
        }
        // ADMIN role requires no auxiliary profile

        String token = jwtTokenProvider.generateTokenFromUsername(user.getUsername(), user.getRole().name());

        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .tokenType("Bearer")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found in database"));

        String token = jwtTokenProvider.generateToken(authentication);

        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(token)
                .tokenType("Bearer")
                .build();
    }
}
