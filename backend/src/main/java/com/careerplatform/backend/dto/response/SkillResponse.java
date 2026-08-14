package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SkillResponse {
    private Long id;
    private String name;
    private Skill.SkillLevel level;

    public static SkillResponse fromEntity(Skill skill) {
        return new SkillResponse(skill.getId(), skill.getName(), skill.getLevel());
    }
}