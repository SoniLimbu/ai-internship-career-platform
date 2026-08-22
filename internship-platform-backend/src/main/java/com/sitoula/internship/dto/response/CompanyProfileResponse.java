package com.sitoula.internship.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyProfileResponse {
    private Long id;
    private String companyName;
    private String website;
    private String location;
    private Boolean isVerified;
}
