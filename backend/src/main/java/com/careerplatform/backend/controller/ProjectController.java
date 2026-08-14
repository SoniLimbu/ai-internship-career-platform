package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.ProjectRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.ProjectResponse;
import com.careerplatform.backend.service.ProjectService;
import com.careerplatform.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectResponse>>> getMyProjects() {
        return ResponseEntity.ok(ApiResponse.of(projectService.getMyProjects(SecurityUtil.currentUserId())));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> add(@Valid @RequestBody ProjectRequest request) {
        ProjectResponse created = projectService.add(SecurityUtil.currentUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Project added", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectResponse>> update(
            @PathVariable Long id, @Valid @RequestBody ProjectRequest request) {
        ProjectResponse updated = projectService.update(SecurityUtil.currentUserId(), id, request);
        return ResponseEntity.ok(ApiResponse.of("Project updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        projectService.delete(SecurityUtil.currentUserId(), id);
        return ResponseEntity.ok(ApiResponse.of("Project deleted", null));
    }
}