package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String title;
    private String description;
    private String technologies;
    private String projectUrl;
    private String repoUrl;

    public static ProjectResponse fromEntity(Project p) {
        return new ProjectResponse(p.getId(), p.getTitle(), p.getDescription(),
                p.getTechnologies(), p.getProjectUrl(), p.getRepoUrl());
    }
}