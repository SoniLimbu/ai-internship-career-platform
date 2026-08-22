package com.sitoula.internship.dto.response;

import com.sitoula.internship.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;
    private Long internshipId;
    private String internshipTitle;
    private String companyName;
    private Long studentId;
    private String studentUsername;
    private ApplicationStatus status;
    private Double aiMatchScore;
    private LocalDateTime appliedAt;
}
