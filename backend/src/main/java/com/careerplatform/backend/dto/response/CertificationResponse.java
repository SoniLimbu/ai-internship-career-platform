package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Certification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CertificationResponse {
    private Long id;
    private String name;
    private String issuer;
    private LocalDate issueDate;
    private String credentialUrl;

    public static CertificationResponse fromEntity(Certification c) {
        return new CertificationResponse(c.getId(), c.getName(), c.getIssuer(),
                c.getIssueDate(), c.getCredentialUrl());
    }
}