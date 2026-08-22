package com.sitoula.internship.service;

import com.sitoula.internship.dto.request.InternshipRequest;
import com.sitoula.internship.dto.response.InternshipResponse;
import com.sitoula.internship.entity.CompanyProfile;
import com.sitoula.internship.entity.Internship;
import com.sitoula.internship.entity.Skill;
import com.sitoula.internship.exception.ResourceNotFoundException;
import com.sitoula.internship.exception.UnauthorizedActionException;
import com.sitoula.internship.repository.InternshipRepository;
import com.sitoula.internship.repository.SkillRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final SkillRepository skillRepository;
    private final CompanyService companyService;

    @Transactional(readOnly = true)
    public List<InternshipResponse> searchInternships(String location, String skill, String type) {
        Specification<Internship> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.isTrue(root.get("isActive")));

            if (StringUtils.hasText(location)) {
                predicates.add(cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%"));
            }
            if (StringUtils.hasText(type)) {
                predicates.add(cb.like(cb.lower(root.get("duration")), "%" + type.toLowerCase() + "%"));
            }
            if (StringUtils.hasText(skill)) {
                query.distinct(true);
                var skillJoin = root.join("requiredSkills");
                predicates.add(cb.like(cb.lower(skillJoin.get("name")), "%" + skill.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return internshipRepository.findAll(spec).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InternshipResponse getInternshipById(Long id) {
        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", id));
        return toResponse(internship);
    }

    @Transactional
    public InternshipResponse createInternship(String companyUsername, InternshipRequest request) {
        CompanyProfile company = companyService.getCompanyProfileByUsername(companyUsername);

        Internship internship = Internship.builder()
                .company(company)
                .title(request.getTitle())
                .location(request.getLocation())
                .duration(request.getDuration())
                .stipend(request.getStipend())
                .openings(request.getOpenings())
                .isActive(request.getIsActive() != null ? request.getIsActive() : true)
                .requiredSkills(resolveSkills(request.getRequiredSkills()))
                .build();

        return toResponse(internshipRepository.save(internship));
    }

    @Transactional
    public InternshipResponse updateInternship(String companyUsername, Long internshipId, InternshipRequest request) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));

        assertOwnership(companyUsername, internship);

        if (request.getTitle() != null) internship.setTitle(request.getTitle());
        if (request.getLocation() != null) internship.setLocation(request.getLocation());
        if (request.getDuration() != null) internship.setDuration(request.getDuration());
        if (request.getStipend() != null) internship.setStipend(request.getStipend());
        if (request.getOpenings() != null) internship.setOpenings(request.getOpenings());
        if (request.getIsActive() != null) internship.setIsActive(request.getIsActive());
        if (request.getRequiredSkills() != null) internship.setRequiredSkills(resolveSkills(request.getRequiredSkills()));

        return toResponse(internshipRepository.save(internship));
    }

    @Transactional
    public void deleteInternship(String companyUsername, Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(() -> new ResourceNotFoundException("Internship", "id", internshipId));

        assertOwnership(companyUsername, internship);
        internshipRepository.delete(internship);
    }

    private void assertOwnership(String companyUsername, Internship internship) {
        if (!internship.getCompany().getUser().getUsername().equals(companyUsername)) {
            throw new UnauthorizedActionException("You do not own this internship posting");
        }
    }

    private Set<Skill> resolveSkills(Set<String> skillNames) {
        if (skillNames == null) return Set.of();
        return skillNames.stream()
                .map(name -> skillRepository.findByNameIgnoreCase(name.trim())
                        .orElseGet(() -> skillRepository.save(Skill.builder().name(name.trim()).build())))
                .collect(Collectors.toSet());
    }

    private InternshipResponse toResponse(Internship internship) {
        return InternshipResponse.builder()
                .id(internship.getId())
                .title(internship.getTitle())
                .location(internship.getLocation())
                .duration(internship.getDuration())
                .stipend(internship.getStipend())
                .openings(internship.getOpenings())
                .isActive(internship.getIsActive())
                .requiredSkills(internship.getRequiredSkills().stream().map(Skill::getName).collect(Collectors.toSet()))
                .companyName(internship.getCompany().getCompanyName())
                .companyId(internship.getCompany().getId())
                .createdAt(internship.getCreatedAt())
                .build();
    }
}
