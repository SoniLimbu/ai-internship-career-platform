package com.careerplatform.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProfileRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    private String phone;
    private String bio;
    private String university;
    private String major;
    private String location;
    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;
}