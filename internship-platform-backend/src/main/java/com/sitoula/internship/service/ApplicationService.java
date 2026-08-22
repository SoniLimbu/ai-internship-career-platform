package com.sitoula.internship.service;

import com.sitoula.internship.dto.request.ApplicationStatusUpdateRequest;
import com.sitoula.internship.dto.response.ApplicationResponse;
import com.sitoula.internship.entity.Application;
import com.sitoula.internship.entity.Internship;
import com.sitoula.internship.entity.StudentProfile;
import com.sitoula.internship.exception.DuplicateResourceException;
import com.sitoula.internship.exception.ResourceNotFoundException;
import com.sitoula.internship.exception.UnauthorizedActionException;
import com.sitoula.internship.repository.ApplicationRepository;
import com.sitoula.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final InternshipRepository internshipRepository;
    private final StudentService studentService;
    private final MatchScoreService matchScoreService;

    @Transactional
    public ApplicationResponse apply(String studentUsername, Long internshipId) {
        StudentProfile student = studentService.getStudentProfileByUsername(studentUsername);
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));

        if (applicationRepository.existsByStudentIdAndInternshipId(student.getId(), internshipId)) {
            throw new DuplicateResourceException("You have already applied to this internship");
        }

        double matchScore = matchScoreService.calculateMatchScore(student, internship);

        Application application = Application.builder()
                .student(student)
                .internship(internship)
                .aiMatchScore(matchScore)
                .build();

        return toResponse(applicationRepository.save(application));
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getMyApplications(String studentUsername) {
        StudentProfile student = studentService.getStudentProfileByUsername(studentUsername);
        return applicationRepository.findByStudentId(student.getId()).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApplicationResponse> getApplicantsForInternship(String companyUsername, Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));

        if (!internship.getCompany().getUser().getUsername().equals(companyUsername)) {
            throw new UnauthorizedActionException("You do not own this internship posting");
        }

        return applicationRepository.findByInternshipId(internshipId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ApplicationResponse updateStatus(Long applicationId, ApplicationStatusUpdateRequest request) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application", "id", applicationId));
        application.setStatus(request.getStatus());
        return toResponse(applicationRepository.save(application));
    }

    private ApplicationResponse toResponse(Application application) {
        return ApplicationResponse.builder()
                .id(application.getId())
                .internshipId(application.getInternship().getId())
                .internshipTitle(application.getInternship().getTitle())
                .companyName(application.getInternship().getCompany().getCompanyName())
                .studentId(application.getStudent().getId())
                .studentUsername(application.getStudent().getUser().getUsername())
                .status(application.getStatus())
                .aiMatchScore(application.getAiMatchScore())
                .appliedAt(application.getAppliedAt())
                .build();
    }
}
