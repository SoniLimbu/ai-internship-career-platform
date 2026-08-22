package com.sitoula.internship.controller;

import com.sitoula.internship.dto.request.ApplicationStatusUpdateRequest;
import com.sitoula.internship.dto.response.ApiResponse;
import com.sitoula.internship.dto.response.ApplicationResponse;
import com.sitoula.internship.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<ApplicationResponse>> apply(
            Authentication authentication, @RequestBody Map<String, Long> body) {
        Long internshipId = body.get("internshipId");
        ApplicationResponse response = applicationService.apply(authentication.getName(), internshipId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Application submitted successfully", response));
    }

    @GetMapping("/my-applications")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> myApplications(Authentication authentication) {
        List<ApplicationResponse> applications = applicationService.getMyApplications(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(applications));
    }

    @GetMapping("/internship/{internshipId}")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> applicantsForInternship(
            Authentication authentication, @PathVariable Long internshipId) {
        List<ApplicationResponse> applicants = applicationService.getApplicantsForInternship(authentication.getName(), internshipId);
        return ResponseEntity.ok(ApiResponse.success(applicants));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<ApplicationResponse>> updateStatus(
            @PathVariable Long id, @Valid @RequestBody ApplicationStatusUpdateRequest request) {
        ApplicationResponse response = applicationService.updateStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success("Application status updated successfully", response));
    }
}
