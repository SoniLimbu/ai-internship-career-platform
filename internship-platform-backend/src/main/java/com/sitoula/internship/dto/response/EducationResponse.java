package com.sitoula.internship.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationResponse {
    private Long id;
    private String institution;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
