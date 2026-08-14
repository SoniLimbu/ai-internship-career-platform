package com.careerplatform.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {

    @NotNull(message = "Internship id is required")
    private Long internshipId;

    private String coverNote;
}