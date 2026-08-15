package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.response.AdminStudentSummaryResponse;
import com.careerplatform.backend.enums.Role;
import com.careerplatform.backend.entity.User;
import com.careerplatform.backend.exception.DuplicateResourceException;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.StudentProfileRepository;
import com.careerplatform.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<AdminStudentSummaryResponse> getAllStudents() {
        return studentProfileRepository.findAll().stream()
                .map(AdminStudentSummaryResponse::fromEntity).toList();
    }

    @Transactional
    public void setStudentAccountEnabled(Long studentProfileId, boolean enabled) {
        var profile = studentProfileRepository.findById(studentProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        User user = profile.getUser();
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    public void createAdminAccount(String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("An account with this email already exists");
        }
        User admin = User.builder()
                .email(email)
                .password(passwordEncoder.encode(rawPassword))
                .role(Role.ADMIN)
                .enabled(true)
                .build();
        userRepository.save(admin);
    }
}
