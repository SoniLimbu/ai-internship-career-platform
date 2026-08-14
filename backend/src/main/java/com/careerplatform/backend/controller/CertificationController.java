package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.CertificationRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.CertificationResponse;
import com.careerplatform.backend.service.CertificationService;
import com.careerplatform.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CertificationResponse>>> getMyCertifications() {
        return ResponseEntity.ok(ApiResponse.of(certificationService.getMyCertifications(SecurityUtil.currentUserId())));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CertificationResponse>> add(@Valid @RequestBody CertificationRequest request) {
        CertificationResponse created = certificationService.add(SecurityUtil.currentUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Certification added", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CertificationResponse>> update(
            @PathVariable Long id, @Valid @RequestBody CertificationRequest request) {
        CertificationResponse updated = certificationService.update(SecurityUtil.currentUserId(), id, request);
        return ResponseEntity.ok(ApiResponse.of("Certification updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        certificationService.delete(SecurityUtil.currentUserId(), id);
        return ResponseEntity.ok(ApiResponse.of("Certification deleted", null));
    }
}