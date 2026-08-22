package com.sitoula.internship.controller;

import com.sitoula.internship.dto.request.InternshipRequest;
import com.sitoula.internship.dto.response.ApiResponse;
import com.sitoula.internship.dto.response.InternshipResponse;
import com.sitoula.internship.service.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InternshipResponse>>> listInternships(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String type) {
        List<InternshipResponse> internships = internshipService.searchInternships(location, skill, type);
        return ResponseEntity.ok(ApiResponse.success(internships));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InternshipResponse>> getInternship(@PathVariable Long id) {
        InternshipResponse internship = internshipService.getInternshipById(id);
        return ResponseEntity.ok(ApiResponse.success(internship));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InternshipResponse>> createInternship(
            Authentication authentication, @Valid @RequestBody InternshipRequest request) {
        InternshipResponse response = internshipService.createInternship(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Internship posted successfully", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InternshipResponse>> updateInternship(
            Authentication authentication, @PathVariable Long id, @RequestBody InternshipRequest request) {
        InternshipResponse response = internshipService.updateInternship(authentication.getName(), id, request);
        return ResponseEntity.ok(ApiResponse.success("Internship updated successfully", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInternship(
            Authentication authentication, @PathVariable Long id) {
        internshipService.deleteInternship(authentication.getName(), id);
        return ResponseEntity.ok(ApiResponse.success("Internship deleted successfully", null));
    }
}
