package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.ApplicationRequest;
import com.careerplatform.backend.dto.response.ApplicationResponse;
import com.careerplatform.backend.entity.Application;
import com.careerplatform.backend.entity.Internship;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.DuplicateResourceException;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.ApplicationRepository;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final InternshipService internshipService;

    @Transactional
    public ApplicationResponse apply(Long userId, ApplicationRequest request) {
        StudentProfile student = studentProfile(userId);
        Internship internship = internshipService.findEntity(request.getInternshipId());

        if (applicationRepository.existsByStudentIdAndInternshipId(student.getId(), internship.getId())) {
            throw new DuplicateResourceException("You have already applied to this internship");
        }

        Application application = Application.builder()
                .student(student)
                .internship(internship)
                .coverNote(request.getCoverNote())
                .status(Application.ApplicationStatus.PENDING)
                .build();

        return ApplicationResponse.fromEntity(applicationRepository.save(application));
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getMyApplications(Long userId) {
        StudentProfile student = studentProfile(userId);
        return applicationRepository.findByStudentId(student.getId()).stream()
                .map(ApplicationResponse::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public ApplicationResponse getMyApplication(Long userId, Long applicationId) {
        StudentProfile student = studentProfile(userId);
        Application application = applicationRepository.findByIdAndStudentId(applicationId, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found for this account"));
        return ApplicationResponse.fromEntity(application);
    }

    @Transactional
    public void withdraw(Long userId, Long applicationId) {
        StudentProfile student = studentProfile(userId);
        Application application = applicationRepository.findByIdAndStudentId(applicationId, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Application not found for this account"));
        applicationRepository.delete(application);
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getAllForInternship(Long internshipId) {
        return applicationRepository.findByInternshipId(internshipId).stream()
                .map(ApplicationResponse::fromEntity).toList();
    }

    @Transactional
    public ApplicationResponse updateStatus(Long applicationId, Application.ApplicationStatus status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
        application.setStatus(status);
        return ApplicationResponse.fromEntity(applicationRepository.save(application));
    }

    private StudentProfile studentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}