package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentProfileResponse {
    private Long id;
    private String email;
    private String fullName;
    private String phone;
    private String bio;
    private String university;
    private String major;
    private String location;
    private String linkedinUrl;
    private String githubUrl;
    private String portfolioUrl;

    public static StudentProfileResponse fromEntity(StudentProfile p) {
        return new StudentProfileResponse(
                p.getId(), p.getUser().getEmail(), p.getFullName(), p.getPhone(), p.getBio(),
                p.getUniversity(), p.getMajor(), p.getLocation(),
                p.getLinkedinUrl(), p.getGithubUrl(), p.getPortfolioUrl()
        );
    }
}