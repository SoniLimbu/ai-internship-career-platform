package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompanyResponse {
    private Long id;
    private String name;
    private String website;
    private String industry;
    private String description;
    private String logoUrl;

    public static CompanyResponse fromEntity(Company c) {
        return new CompanyResponse(c.getId(), c.getName(), c.getWebsite(),
                c.getIndustry(), c.getDescription(), c.getLogoUrl());
    }
}