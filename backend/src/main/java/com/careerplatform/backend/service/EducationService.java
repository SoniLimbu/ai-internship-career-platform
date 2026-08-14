package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.EducationRequest;
import com.careerplatform.backend.dto.response.EducationResponse;
import com.careerplatform.backend.entity.Education;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.EducationRepository;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final StudentProfileRepository studentProfileRepository;

    @Transactional(readOnly = true)
    public List<EducationResponse> getMyEducation(Long userId) {
        StudentProfile student = studentProfile(userId);
        return educationRepository.findByStudentId(student.getId()).stream()
                .map(EducationResponse::fromEntity).toList();
    }

    @Transactional
    public EducationResponse add(Long userId, EducationRequest request) {
        StudentProfile student = studentProfile(userId);
        Education education = Education.builder()
                .student(student)
                .institution(request.getInstitution())
                .degreeProgram(request.getDegreeProgram())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .description(request.getDescription())
                .build();
        return EducationResponse.fromEntity(educationRepository.save(education));
    }

    @Transactional
    public EducationResponse update(Long userId, Long id, EducationRequest request) {
        StudentProfile student = studentProfile(userId);
        Education education = educationRepository.findByIdAndStudentId(id, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Education record not found for this account"));

        education.setInstitution(request.getInstitution());
        education.setDegreeProgram(request.getDegreeProgram());
        education.setStartDate(request.getStartDate());
        education.setEndDate(request.getEndDate());
        education.setDescription(request.getDescription());

        return EducationResponse.fromEntity(educationRepository.save(education));
    }

    @Transactional
    public void delete(Long userId, Long id) {
        StudentProfile student = studentProfile(userId);
        Education education = educationRepository.findByIdAndStudentId(id, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Education record not found for this account"));
        educationRepository.delete(education);
    }

    private StudentProfile studentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}