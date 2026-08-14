package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.CompanyRequest;
import com.careerplatform.backend.dto.response.CompanyResponse;
import com.careerplatform.backend.entity.Company;
import com.careerplatform.backend.exception.DuplicateResourceException;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public List<CompanyResponse> getAll() {
        return companyRepository.findAll().stream().map(CompanyResponse::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public CompanyResponse getById(Long id) {
        return CompanyResponse.fromEntity(findEntity(id));
    }

    @Transactional
    public CompanyResponse create(CompanyRequest request) {
        if (companyRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("A company with this name already exists");
        }
        Company company = Company.builder()
                .name(request.getName())
                .website(request.getWebsite())
                .industry(request.getIndustry())
                .description(request.getDescription())
                .logoUrl(request.getLogoUrl())
                .build();
        return CompanyResponse.fromEntity(companyRepository.save(company));
    }

    @Transactional
    public CompanyResponse update(Long id, CompanyRequest request) {
        Company company = findEntity(id);
        company.setName(request.getName());
        company.setWebsite(request.getWebsite());
        company.setIndustry(request.getIndustry());
        company.setDescription(request.getDescription());
        company.setLogoUrl(request.getLogoUrl());
        return CompanyResponse.fromEntity(companyRepository.save(company));
    }

    @Transactional
    public void delete(Long id) {
        companyRepository.delete(findEntity(id));
    }

    Company findEntity(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found"));
    }
}