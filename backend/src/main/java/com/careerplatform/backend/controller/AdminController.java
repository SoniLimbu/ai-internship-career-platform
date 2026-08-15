package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.ApplicationStatusUpdateRequest;
import com.careerplatform.backend.dto.request.CreateAdminRequest;
import com.careerplatform.backend.dto.response.AdminStudentSummaryResponse;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.ApplicationResponse;
import com.careerplatform.backend.service.AdminService;
import com.careerplatform.backend.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ApplicationService applicationService;

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<AdminStudentSummaryResponse>>> getAllStudents() {
        return ResponseEntity.ok(ApiResponse.of(adminService.getAllStudents()));
    }

    @PatchMapping("/students/{id}/enabled")
    public ResponseEntity<ApiResponse<Void>> setStudentEnabled(
            @PathVariable Long id, @RequestParam boolean enabled) {
        adminService.setStudentAccountEnabled(id, enabled);
        return ResponseEntity.ok(ApiResponse.of("Student account updated", null));
    }

    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<Void>> createAdminAccount(@Valid @RequestBody CreateAdminRequest request) {
        adminService.createAdminAccount(request.getEmail(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Admin account created", null));
    }

    @GetMapping("/internships/{internshipId}/applications")
    public ResponseEntity<ApiResponse<List<ApplicationResponse>>> getApplicationsForInternship(
            @PathVariable Long internshipId) {
        return ResponseEntity.ok(ApiResponse.of(applicationService.getAllForInternship(internshipId)));
    }

    @PatchMapping("/applications/{id}/status")
    public ResponseEntity<ApiResponse<ApplicationResponse>> updateApplicationStatus(
            @PathVariable Long id, @Valid @RequestBody ApplicationStatusUpdateRequest request) {
        ApplicationResponse updated = applicationService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(ApiResponse.of("Application status updated", updated));
    }
}