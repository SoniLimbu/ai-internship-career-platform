package com.careerplatform.backend.dto.request;

import com.careerplatform.backend.entity.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillRequest {

    @NotBlank(message = "Skill name is required")
    private String name;

    @NotNull(message = "Skill level is required")
    private Skill.SkillLevel level;
}