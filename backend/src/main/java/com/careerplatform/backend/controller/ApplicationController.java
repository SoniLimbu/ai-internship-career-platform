package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.ApplicationRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.ApplicationResponse;
import com.careerplatform.backend.service.ApplicationService;
import com.careerplatform.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApiResponse<ApplicationResponse>> apply(@Valid @RequestBody ApplicationRequest request) {
        ApplicationResponse application = applicationService.apply(SecurityUtil.currentUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Application submitted", application));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> getMyApplications() {
        return ResponseEntity.ok(ApiResponse.of(applicationService.getMyApplications(SecurityUtil.currentUserId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ApplicationResponse>> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.of(applicationService.getMyApplication(SecurityUtil.currentUserId(), id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> withdraw(@PathVariable Long id) {
        applicationService.withdraw(SecurityUtil.currentUserId(), id);
        return ResponseEntity.ok(ApiResponse.of("Application withdrawn", null));
    }
}