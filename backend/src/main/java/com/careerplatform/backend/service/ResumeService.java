package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.response.ResumeResponse;
import com.careerplatform.backend.entity.Resume;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.ResumeRepository;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final StudentProfileRepository studentProfileRepository;

    @Value("${app.resume.storage-path}")
    private String storagePath;

    @Transactional
    public ResumeResponse upload(Long userId, MultipartFile file) {
        StudentProfile student = studentProfile(userId);

        try {
            Path dir = Path.of(storagePath);
            Files.createDirectories(dir);

            String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path target = dir.resolve(storedFileName);
            file.transferTo(target);

            Resume resume = resumeRepository.findByStudentId(student.getId()).orElse(null);
            if (resume == null) {
                resume = Resume.builder().student(student).build();
            }
            resume.setOriginalFileName(file.getOriginalFilename());
            resume.setStoredFileName(storedFileName);
            resume.setStoragePath(target.toString());
            resume.setContentType(file.getContentType());
            resume.setFileSizeBytes(file.getSize());

            return ResumeResponse.fromEntity(resumeRepository.save(resume));
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to store resume file", e);
        }
    }

    @Transactional(readOnly = true)
    public ResumeResponse getMyResumeMetadata(Long userId) {
        StudentProfile student = studentProfile(userId);
        Resume resume = resumeRepository.findByStudentId(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No resume uploaded yet"));
        return ResumeResponse.fromEntity(resume);
    }

    @Transactional(readOnly = true)
    public Resume getMyResumeEntity(Long userId) {
        StudentProfile student = studentProfile(userId);
        return resumeRepository.findByStudentId(student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No resume uploaded yet"));
    }

    public Resource loadAsResource(Resume resume) {
        return new FileSystemResource(resume.getStoragePath());
    }

    private StudentProfile studentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}