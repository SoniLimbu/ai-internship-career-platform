package com.sitoula.internship.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Service
public class FileStorageService {

    @Value("${app.file.upload-dir}")
    private String uploadDir;

    private Path resolveUploadPath() {
        Path path = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory: " + path, ex);
        }
        return path;
    }

    public String storeResume(MultipartFile file, Long studentId) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload an empty file");
        }

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : "resume.pdf");
        String extension = originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".pdf";

        if (!extension.equalsIgnoreCase(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed for resume upload");
        }

        String storedFilename = "student_" + studentId + "_" + UUID.randomUUID() + extension;
        Path targetLocation = resolveUploadPath().resolve(storedFilename);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.error("Failed to store resume file for student {}", studentId, ex);
            throw new RuntimeException("Failed to store resume file. Please try again.", ex);
        }

        return targetLocation.toString();
    }
}
