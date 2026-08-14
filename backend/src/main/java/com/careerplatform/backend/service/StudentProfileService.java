package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.StudentProfileRequest;
import com.careerplatform.backend.dto.response.StudentProfileResponse;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    @Transactional(readOnly = true)
    public StudentProfileResponse getMyProfile(Long userId) {
        return StudentProfileResponse.fromEntity(getByUserId(userId));
    }

    @Transactional
    public StudentProfileResponse updateMyProfile(Long userId, StudentProfileRequest request) {
        StudentProfile profile = getByUserId(userId);

        profile.setFullName(request.getFullName());
        profile.setPhone(request.getPhone());
        profile.setBio(request.getBio());
        profile.setUniversity(request.getUniversity());
        profile.setMajor(request.getMajor());
        profile.setLocation(request.getLocation());
        profile.setLinkedinUrl(request.getLinkedinUrl());
        profile.setGithubUrl(request.getGithubUrl());
        profile.setPortfolioUrl(request.getPortfolioUrl());

        return StudentProfileResponse.fromEntity(studentProfileRepository.save(profile));
    }

    private StudentProfile getByUserId(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}