package com.sitoula.internship.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CertificationRequest {

    @NotBlank(message = "Certification name is required")
    private String name;

    private String issuer;

    private LocalDate issueDate;

    private String credentialUrl;
}
