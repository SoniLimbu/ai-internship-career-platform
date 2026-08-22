package com.sitoula.internship.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class InternshipRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String location;

    private String duration;

    private Double stipend;

    @NotNull(message = "Number of openings is required")
    private Integer openings;

    private Set<String> requiredSkills;

    private Boolean isActive;
}
