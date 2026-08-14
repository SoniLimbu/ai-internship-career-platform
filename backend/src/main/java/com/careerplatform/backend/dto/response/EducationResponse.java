package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Education;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EducationResponse {
    private Long id;
    private String institution;
    private String degreeProgram;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

    public static EducationResponse fromEntity(Education e) {
        return new EducationResponse(e.getId(), e.getInstitution(), e.getDegreeProgram(),
                e.getStartDate(), e.getEndDate(), e.getDescription());
    }
}