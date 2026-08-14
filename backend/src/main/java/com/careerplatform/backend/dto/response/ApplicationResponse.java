package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApplicationResponse {
    private Long id;
    private Long studentId;
    private String studentName;
    private InternshipResponse internship;
    private Application.ApplicationStatus status;
    private String coverNote;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;

    public static ApplicationResponse fromEntity(Application a) {
        return new ApplicationResponse(
                a.getId(),
                a.getStudent().getId(),
                a.getStudent().getFullName(),
                InternshipResponse.fromEntity(a.getInternship()),
                a.getStatus(),
                a.getCoverNote(),
                a.getAppliedAt(),
                a.getUpdatedAt()
        );
    }
}