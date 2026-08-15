package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.LoginRequest;
import com.careerplatform.backend.dto.request.RegisterRequest;
import com.careerplatform.backend.dto.response.AuthResponse;
import com.careerplatform.backend.enums.Role;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.entity.User;
import com.careerplatform.backend.exception.DuplicateResourceException;
import com.careerplatform.backend.repository.StudentProfileRepository;
import com.careerplatform.backend.repository.UserRepository;
import com.careerplatform.backend.security.JwtService;
import com.careerplatform.backend.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("An account with this email already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .enabled(true)
                .build();
        user = userRepository.save(user);

        StudentProfile profile = StudentProfile.builder()
                .user(user)
                .fullName(request.getFullName())
                .build();
        studentProfileRepository.save(profile);

        UserPrincipal principal = new UserPrincipal(user);
        String token = jwtService.generateToken(principal);

        return new AuthResponse(token, user.getEmail(), user.getRole().name(), user.getId());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("User disappeared after authentication"));

        UserPrincipal principal = new UserPrincipal(user);
        String token = jwtService.generateToken(principal);

        return new AuthResponse(token, user.getEmail(), user.getRole().name(), user.getId());
    }

    // Logout is stateless (JWT): the frontend simply discards the token.
}
