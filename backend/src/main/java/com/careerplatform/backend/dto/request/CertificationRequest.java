package com.careerplatform.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CertificationRequest {

    @NotBlank(message = "Certificate name is required")
    private String name;

    @NotBlank(message = "Issuer is required")
    private String issuer;

    private LocalDate issueDate;
    private String credentialUrl;
}