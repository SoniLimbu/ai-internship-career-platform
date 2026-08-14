package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.StudentProfileRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.StudentProfileResponse;
import com.careerplatform.backend.service.StudentProfileService;
import com.careerplatform.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<ApiResponse<StudentProfileResponse>> getMyProfile() {
        return ResponseEntity.ok(ApiResponse.of(studentProfileService.getMyProfile(SecurityUtil.currentUserId())));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<StudentProfileResponse>> updateMyProfile(
            @Valid @RequestBody StudentProfileRequest request) {
        StudentProfileResponse updated =
                studentProfileService.updateMyProfile(SecurityUtil.currentUserId(), request);
        return ResponseEntity.ok(ApiResponse.of("Profile updated", updated));
    }
}