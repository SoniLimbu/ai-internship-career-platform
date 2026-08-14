package com.careerplatform.backend.dto.request;

import com.careerplatform.backend.entity.Internship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InternshipRequest {

    @NotNull(message = "Company id is required")
    private Long companyId;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String location;
    private Internship.WorkMode workMode;
    private Internship.InternshipType type;
    private Double stipend;
    private LocalDate applicationDeadline;
    private String requiredSkills;
}