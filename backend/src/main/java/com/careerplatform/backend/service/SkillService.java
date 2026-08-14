package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.SkillRequest;
import com.careerplatform.backend.dto.response.SkillResponse;
import com.careerplatform.backend.entity.Skill;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.SkillRepository;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final StudentProfileRepository studentProfileRepository;

    @Transactional(readOnly = true)
    public List<SkillResponse> getMySkills(Long userId) {
        StudentProfile student = getStudentProfileByUserId(userId);
        return skillRepository.findByStudentId(student.getId()).stream()
                .map(SkillResponse::fromEntity)
                .toList();
    }

    @Transactional
    public SkillResponse addSkill(Long userId, SkillRequest request) {
        StudentProfile student = getStudentProfileByUserId(userId);

        Skill skill = Skill.builder()
                .student(student)
                .name(request.getName())
                .level(request.getLevel())
                .build();

        return SkillResponse.fromEntity(skillRepository.save(skill));
    }

    @Transactional
    public SkillResponse updateSkill(Long userId, Long skillId, SkillRequest request) {
        StudentProfile student = getStudentProfileByUserId(userId);

        Skill skill = skillRepository.findByIdAndStudentId(skillId, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found for this account"));

        skill.setName(request.getName());
        skill.setLevel(request.getLevel());

        return SkillResponse.fromEntity(skillRepository.save(skill));
    }

    @Transactional
    public void deleteSkill(Long userId, Long skillId) {
        StudentProfile student = getStudentProfileByUserId(userId);

        Skill skill = skillRepository.findByIdAndStudentId(skillId, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found for this account"));

        skillRepository.delete(skill);
    }

    private StudentProfile getStudentProfileByUserId(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}