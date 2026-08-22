package com.sitoula.internship.service;

import com.sitoula.internship.dto.response.CompanyProfileResponse;
import com.sitoula.internship.entity.CompanyProfile;
import com.sitoula.internship.exception.ResourceNotFoundException;
import com.sitoula.internship.repository.CompanyProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyProfileRepository companyProfileRepository;

    public CompanyProfile getCompanyProfileByUsername(String username) {
        return companyProfileRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Company profile", "username", username));
    }

    @Transactional(readOnly = true)
    public CompanyProfileResponse getProfile(String username) {
        return toResponse(getCompanyProfileByUsername(username));
    }

    private CompanyProfileResponse toResponse(CompanyProfile profile) {
        return CompanyProfileResponse.builder()
                .id(profile.getId())
                .companyName(profile.getCompanyName())
                .website(profile.getWebsite())
                .location(profile.getLocation())
                .isVerified(profile.getIsVerified())
                .build();
    }
}
