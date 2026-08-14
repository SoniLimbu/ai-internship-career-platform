package com.careerplatform.backend.controller;

import com.careerplatform.backend.dto.request.SkillRequest;
import com.careerplatform.backend.dto.response.ApiResponse;
import com.careerplatform.backend.dto.response.SkillResponse;
import com.careerplatform.backend.service.SkillService;
import com.careerplatform.backend.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SkillResponse>>> getMySkills() {
        List<SkillResponse> skills = skillService.getMySkills(SecurityUtil.currentUserId());
        return ResponseEntity.ok(ApiResponse.of(skills));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SkillResponse>> addSkill(@Valid @RequestBody SkillRequest request) {
        SkillResponse skill = skillService.addSkill(SecurityUtil.currentUserId(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of("Skill added", skill));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SkillResponse>> updateSkill(
            @PathVariable Long id, @Valid @RequestBody SkillRequest request) {
        SkillResponse skill = skillService.updateSkill(SecurityUtil.currentUserId(), id, request);
        return ResponseEntity.ok(ApiResponse.of("Skill updated", skill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(SecurityUtil.currentUserId(), id);
        return ResponseEntity.ok(ApiResponse.of("Skill deleted", null));
    }
}