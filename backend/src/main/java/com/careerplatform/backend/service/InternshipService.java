package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.InternshipRequest;
import com.careerplatform.backend.dto.response.InternshipResponse;
import com.careerplatform.backend.entity.Company;
import com.careerplatform.backend.entity.Internship;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyService companyService;

    @Transactional(readOnly = true)
    public List<InternshipResponse> getAllActive() {
        return internshipRepository.findByActiveTrue().stream()
                .map(InternshipResponse::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public List<InternshipResponse> search(String keyword, String location,
                                           Internship.WorkMode workMode, Internship.InternshipType type) {
        return internshipRepository.search(blankToNull(keyword), blankToNull(location), workMode, type)
                .stream().map(InternshipResponse::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public InternshipResponse getById(Long id) {
        return InternshipResponse.fromEntity(findEntity(id));
    }

    @Transactional
    public InternshipResponse create(InternshipRequest request) {
        Company company = companyService.findEntity(request.getCompanyId());
        Internship internship = Internship.builder()
                .company(company)
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .workMode(request.getWorkMode())
                .type(request.getType())
                .stipend(request.getStipend())
                .applicationDeadline(request.getApplicationDeadline())
                .requiredSkills(request.getRequiredSkills())
                .active(true)
                .build();
        return InternshipResponse.fromEntity(internshipRepository.save(internship));
    }

    @Transactional
    public InternshipResponse update(Long id, InternshipRequest request) {
        Internship internship = findEntity(id);
        Company company = companyService.findEntity(request.getCompanyId());

        internship.setCompany(company);
        internship.setTitle(request.getTitle());
        internship.setDescription(request.getDescription());
        internship.setLocation(request.getLocation());
        internship.setWorkMode(request.getWorkMode());
        internship.setType(request.getType());
        internship.setStipend(request.getStipend());
        internship.setApplicationDeadline(request.getApplicationDeadline());
        internship.setRequiredSkills(request.getRequiredSkills());

        return InternshipResponse.fromEntity(internshipRepository.save(internship));
    }

    @Transactional
    public void setActive(Long id, boolean active) {
        Internship internship = findEntity(id);
        internship.setActive(active);
        internshipRepository.save(internship);
    }

    @Transactional
    public void delete(Long id) {
        internshipRepository.delete(findEntity(id));
    }

    Internship findEntity(Long id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship not found"));
    }

    private String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }
}