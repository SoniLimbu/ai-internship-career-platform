package com.sitoula.internship.controller;

import com.sitoula.internship.dto.request.*;
import com.sitoula.internship.dto.response.*;
import com.sitoula.internship.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> getProfile(Authentication authentication) {
        StudentProfileResponse profile = studentService.getProfile(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success(profile));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> updateProfile(
            Authentication authentication, @Valid @RequestBody StudentProfileUpdateRequest request) {
        StudentProfileResponse profile = studentService.updateProfile(authentication.getName(), request);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", profile));
    }

    @PostMapping("/skills")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> addSkill(
            Authentication authentication, @Valid @RequestBody SkillRequest request) {
        StudentProfileResponse profile = studentService.addSkill(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Skill added successfully", profile));
    }

    @DeleteMapping("/skills/{id}")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> removeSkill(
            Authentication authentication, @PathVariable Long id) {
        StudentProfileResponse profile = studentService.removeSkill(authentication.getName(), id);
        return ResponseEntity.ok(ApiResponse.success("Skill removed successfully", profile));
    }

    @PostMapping("/education")
    public ResponseEntity<ApiResponse<EducationResponse>> addEducation(
            Authentication authentication, @Valid @RequestBody EducationRequest request) {
        EducationResponse response = studentService.addEducation(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Education added successfully", response));
    }

    @PostMapping("/projects")
    public ResponseEntity<ApiResponse<ProjectResponse>> addProject(
            Authentication authentication, @Valid @RequestBody ProjectRequest request) {
        ProjectResponse response = studentService.addProject(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Project added successfully", response));
    }

    @PostMapping("/certifications")
    public ResponseEntity<ApiResponse<CertificationResponse>> addCertification(
            Authentication authentication, @Valid @RequestBody CertificationRequest request) {
        CertificationResponse response = studentService.addCertification(authentication.getName(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Certification added successfully", response));
    }

    @PostMapping(value = "/upload-resume", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<StudentProfileResponse>> uploadResume(
            Authentication authentication, @RequestParam("file") MultipartFile file) {
        StudentProfileResponse profile = studentService.uploadResume(authentication.getName(), file);
        return ResponseEntity.ok(ApiResponse.success("Resume uploaded successfully", profile));
    }
}
