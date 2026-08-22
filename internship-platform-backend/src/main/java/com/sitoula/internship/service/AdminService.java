package com.sitoula.internship.service;

import com.sitoula.internship.dto.response.AdminDashboardResponse;
import com.sitoula.internship.entity.CompanyProfile;
import com.sitoula.internship.entity.Role;
import com.sitoula.internship.entity.User;
import com.sitoula.internship.exception.ResourceNotFoundException;
import com.sitoula.internship.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final CompanyProfileRepository companyProfileRepository;
    private final InternshipRepository internshipRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional(readOnly = true)
    public AdminDashboardResponse getDashboardStats() {
        long totalStudents = studentProfileRepository.count();
        long totalCompanies = companyProfileRepository.count();
        long activeInternships = internshipRepository.findByIsActiveTrue().size();
        long totalApplications = applicationRepository.count();

        return AdminDashboardResponse.builder()
                .totalStudents(totalStudents)
                .totalCompanies(totalCompanies)
                .activeInternships(activeInternships)
                .totalApplications(totalApplications)
                .build();
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public CompanyProfile verifyCompany(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (user.getRole() != Role.COMPANY) {
            throw new IllegalArgumentException("User with id " + userId + " is not a COMPANY account");
        }

        CompanyProfile profile = companyProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Company profile", "userId", userId));

        profile.setIsVerified(true);
        return companyProfileRepository.save(profile);
    }
}
