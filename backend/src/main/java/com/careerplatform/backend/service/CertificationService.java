package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.CertificationRequest;
import com.careerplatform.backend.dto.response.CertificationResponse;
import com.careerplatform.backend.entity.Certification;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.CertificationRepository;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final StudentProfileRepository studentProfileRepository;

    @Transactional(readOnly = true)
    public List<CertificationResponse> getMyCertifications(Long userId) {
        StudentProfile student = studentProfile(userId);
        return certificationRepository.findByStudentId(student.getId()).stream()
                .map(CertificationResponse::fromEntity).toList();
    }

    @Transactional
    public CertificationResponse add(Long userId, CertificationRequest request) {
        StudentProfile student = studentProfile(userId);
        Certification cert = Certification.builder()
                .student(student)
                .name(request.getName())
                .issuer(request.getIssuer())
                .issueDate(request.getIssueDate())
                .credentialUrl(request.getCredentialUrl())
                .build();
        return CertificationResponse.fromEntity(certificationRepository.save(cert));
    }

    @Transactional
    public CertificationResponse update(Long userId, Long id, CertificationRequest request) {
        StudentProfile student = studentProfile(userId);
        Certification cert = certificationRepository.findByIdAndStudentId(id, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Certification not found for this account"));

        cert.setName(request.getName());
        cert.setIssuer(request.getIssuer());
        cert.setIssueDate(request.getIssueDate());
        cert.setCredentialUrl(request.getCredentialUrl());

        return CertificationResponse.fromEntity(certificationRepository.save(cert));
    }

    @Transactional
    public void delete(Long userId, Long id) {
        StudentProfile student = studentProfile(userId);
        Certification cert = certificationRepository.findByIdAndStudentId(id, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Certification not found for this account"));
        certificationRepository.delete(cert);
    }

    private StudentProfile studentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}