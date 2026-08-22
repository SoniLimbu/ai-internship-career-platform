package com.sitoula.internship.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternshipResponse {
    private Long id;
    private String title;
    private String location;
    private String duration;
    private Double stipend;
    private Integer openings;
    private Boolean isActive;
    private Set<String> requiredSkills;
    private String companyName;
    private Long companyId;
    private LocalDateTime createdAt;
}
