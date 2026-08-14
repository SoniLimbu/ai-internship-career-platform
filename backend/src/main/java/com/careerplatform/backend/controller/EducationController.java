package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.EducationRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.EducationResponse;
import com.careerplatform.backend.service.EducationService;
import com.careerplatform.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EducationResponse>>> getMyEducation() {
        return ResponseEntity.ok(ApiResponse.of(educationService.getMyEducation(SecurityUtil.currentUserId())));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EducationResponse>> add(@Valid @RequestBody EducationRequest request) {
        EducationResponse created = educationService.add(SecurityUtil.currentUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Education added", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EducationResponse>> update(
            @PathVariable Long id, @Valid @RequestBody EducationRequest request) {
        EducationResponse updated = educationService.update(SecurityUtil.currentUserId(), id, request);
        return ResponseEntity.ok(ApiResponse.of("Education updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        educationService.delete(SecurityUtil.currentUserId(), id);
        return ResponseEntity.ok(ApiResponse.of("Education deleted", null));
    }
}