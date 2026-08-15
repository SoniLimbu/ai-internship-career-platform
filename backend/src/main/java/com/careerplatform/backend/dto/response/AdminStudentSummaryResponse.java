package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.StudentProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminStudentSummaryResponse {
    private Long studentId;
    private String fullName;
    private String email;
    private String university;
    private boolean accountEnabled;

    public static AdminStudentSummaryResponse fromEntity(StudentProfile p) {
        return new AdminStudentSummaryResponse(
                p.getId(), p.getFullName(), p.getUser().getEmail(), p.getUniversity(), p.getUser().isEnabled()
        );
    }
}