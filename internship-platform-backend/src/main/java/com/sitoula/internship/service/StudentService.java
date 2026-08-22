package com.sitoula.internship.service;

import com.sitoula.internship.dto.request.*;
import com.sitoula.internship.dto.response.*;
import com.sitoula.internship.entity.*;
import com.sitoula.internship.exception.ResourceNotFoundException;
import com.sitoula.internship.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentProfileRepository studentProfileRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final ProjectRepository projectRepository;
    private final CertificationRepository certificationRepository;
    private final FileStorageService fileStorageService;

    // ---------- Profile ----------

    public StudentProfile getStudentProfileByUsername(String username) {
        return studentProfileRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile", "username", username));
    }

    @Transactional(readOnly = true)
    public StudentProfileResponse getProfile(String username) {
        return toProfileResponse(getStudentProfileByUsername(username));
    }

    @Transactional
    public StudentProfileResponse updateProfile(String username, StudentProfileUpdateRequest request) {
        StudentProfile profile = getStudentProfileByUsername(username);
        if (request.getUniversity() != null) profile.setUniversity(request.getUniversity());
        if (request.getDegree() != null) profile.setDegree(request.getDegree());
        if (request.getBio() != null) profile.setBio(request.getBio());
        return toProfileResponse(studentProfileRepository.save(profile));
    }

    // ---------- Skills ----------

    @Transactional
    public StudentProfileResponse addSkill(String username, SkillRequest request) {
        StudentProfile profile = getStudentProfileByUsername(username);
        Skill skill = skillRepository.findByNameIgnoreCase(request.getName().trim())
                .orElseGet(() -> skillRepository.save(Skill.builder().name(request.getName().trim()).build()));
        profile.getSkills().add(skill);
        return toProfileResponse(studentProfileRepository.save(profile));
    }

    @Transactional
    public StudentProfileResponse removeSkill(String username, Long skillId) {
        StudentProfile profile = getStudentProfileByUsername(username);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));
        profile.getSkills().remove(skill);
        return toProfileResponse(studentProfileRepository.save(profile));
    }

    // ---------- Education ----------

    @Transactional
    public EducationResponse addEducation(String username, EducationRequest request) {
        StudentProfile profile = getStudentProfileByUsername(username);
        Education education = Education.builder()
                .institution(request.getInstitution())
                .degree(request.getDegree())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .description(request.getDescription())
                .studentProfile(profile)
                .build();
        return toEducationResponse(educationRepository.save(education));
    }

    // ---------- Projects ----------

    @Transactional
    public ProjectResponse addProject(String username, ProjectRequest request) {
        StudentProfile profile = getStudentProfileByUsername(username);
        Project project = Project.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies())
                .projectLink(request.getProjectLink())
                .studentProfile(profile)
                .build();
        return toProjectResponse(projectRepository.save(project));
    }

    // ---------- Certifications ----------

    @Transactional
    public CertificationResponse addCertification(String username, CertificationRequest request) {
        StudentProfile profile = getStudentProfileByUsername(username);
        Certification certification = Certification.builder()
                .name(request.getName())
                .issuer(request.getIssuer())
                .issueDate(request.getIssueDate())
                .credentialUrl(request.getCredentialUrl())
                .studentProfile(profile)
                .build();
        return toCertificationResponse(certificationRepository.save(certification));
    }

    // ---------- Resume ----------

    @Transactional
    public StudentProfileResponse uploadResume(String username, MultipartFile file) {
        StudentProfile profile = getStudentProfileByUsername(username);
        String storedPath = fileStorageService.storeResume(file, profile.getId());
        profile.setResumeFilePath(storedPath);
        return toProfileResponse(studentProfileRepository.save(profile));
    }

    // ---------- Mappers ----------

    private StudentProfileResponse toProfileResponse(StudentProfile profile) {
        Set<String> skillNames = profile.getSkills().stream().map(Skill::getName).collect(Collectors.toSet());
        List<EducationResponse> educations = profile.getEducations().stream()
                .map(this::toEducationResponse).collect(Collectors.toList());
        List<ProjectResponse> projects = profile.getProjects().stream()
                .map(this::toProjectResponse).collect(Collectors.toList());
        List<CertificationResponse> certifications = profile.getCertifications().stream()
                .map(this::toCertificationResponse).collect(Collectors.toList());

        return StudentProfileResponse.builder()
                .id(profile.getId())
                .username(profile.getUser().getUsername())
                .email(profile.getUser().getEmail())
                .university(profile.getUniversity())
                .degree(profile.getDegree())
                .bio(profile.getBio())
                .resumeFilePath(profile.getResumeFilePath())
                .skills(skillNames)
                .educations(educations)
                .projects(projects)
                .certifications(certifications)
                .build();
    }

    private EducationResponse toEducationResponse(Education e) {
        return EducationResponse.builder()
                .id(e.getId())
                .institution(e.getInstitution())
                .degree(e.getDegree())
                .startDate(e.getStartDate())
                .endDate(e.getEndDate())
                .description(e.getDescription())
                .build();
    }

    private ProjectResponse toProjectResponse(Project p) {
        return ProjectResponse.builder()
                .id(p.getId())
                .title(p.getTitle())
                .description(p.getDescription())
                .technologies(p.getTechnologies())
                .projectLink(p.getProjectLink())
                .build();
    }

    private CertificationResponse toCertificationResponse(Certification c) {
        return CertificationResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .issuer(c.getIssuer())
                .issueDate(c.getIssueDate())
                .credentialUrl(c.getCredentialUrl())
                .build();
    }
}
