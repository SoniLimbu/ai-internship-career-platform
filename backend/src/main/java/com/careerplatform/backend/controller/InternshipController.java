package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.InternshipRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.InternshipResponse;
import com.careerplatform.backend.entity.Internship;
import com.careerplatform.backend.service.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InternshipResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.of(internshipService.getAllActive()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<InternshipResponse>>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Internship.WorkMode workMode,
            @RequestParam(required = false) Internship.InternshipType type
    ) {
        return ResponseEntity.ok(ApiResponse.of(internshipService.search(keyword, location, workMode, type)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InternshipResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.of(internshipService.getById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<InternshipResponse>> create(@Valid @RequestBody InternshipRequest request) {
        InternshipResponse created = internshipService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Internship created", created));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<InternshipResponse>> update(
            @PathVariable Long id, @Valid @RequestBody InternshipRequest request) {
        return ResponseEntity.ok(ApiResponse.of("Internship updated", internshipService.update(id, request)));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> setActive(@PathVariable Long id, @RequestParam boolean active) {
        internshipService.setActive(id, active);
        return ResponseEntity.ok(ApiResponse.of("Internship status updated", null));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        internshipService.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Internship deleted", null));
    }
}