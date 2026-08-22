package com.sitoula.internship.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String university;
    private String degree;
    private String bio;
    private String resumeFilePath;
    private Set<String> skills;
    private List<EducationResponse> educations;
    private List<ProjectResponse> projects;
    private List<CertificationResponse> certifications;
}
