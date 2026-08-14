package com.careerplatform.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EducationRequest {

    @NotBlank(message = "Institution is required")
    private String institution;

    @NotBlank(message = "Degree/program is required")
    private String degreeProgram;

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}