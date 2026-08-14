package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Internship;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InternshipResponse {
    private Long id;
    private CompanyResponse company;
    private String title;
    private String description;
    private String location;
    private Internship.WorkMode workMode;
    private Internship.InternshipType type;
    private Double stipend;
    private LocalDate applicationDeadline;
    private String requiredSkills;
    private boolean active;

    public static InternshipResponse fromEntity(Internship i) {
        return new InternshipResponse(
                i.getId(), CompanyResponse.fromEntity(i.getCompany()), i.getTitle(), i.getDescription(),
                i.getLocation(), i.getWorkMode(), i.getType(), i.getStipend(),
                i.getApplicationDeadline(), i.getRequiredSkills(), i.isActive()
        );
    }
}