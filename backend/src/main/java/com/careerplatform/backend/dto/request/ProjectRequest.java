package com.careerplatform.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;
    private String technologies;
    private String projectUrl;
    private String repoUrl;
}