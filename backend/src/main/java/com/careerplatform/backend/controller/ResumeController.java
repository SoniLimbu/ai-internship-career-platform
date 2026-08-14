package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.ResumeResponse;
import com.careerplatform.backend.entity.Resume;
import com.careerplatform.backend.service.ResumeService;
import com.careerplatform.backend.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<ResumeResponse>> upload(@RequestParam("file") MultipartFile file) {
        ResumeResponse response = resumeService.upload(SecurityUtil.currentUserId(), file);
        return ResponseEntity.ok(ApiResponse.of("Resume uploaded", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<ResumeResponse>> getMetadata() {
        return ResponseEntity.ok(ApiResponse.of(resumeService.getMyResumeMetadata(SecurityUtil.currentUserId())));
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> download() {
        Resume resume = resumeService.getMyResumeEntity(SecurityUtil.currentUserId());
        Resource file = resumeService.loadAsResource(resume);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(
                        resume.getContentType() != null ? resume.getContentType() : "application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resume.getOriginalFileName() + "\"")
                .body(file);
    }
}