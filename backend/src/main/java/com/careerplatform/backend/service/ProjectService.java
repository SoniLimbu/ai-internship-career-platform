package com.careerplatform.backend.service;

import com.careerplatform.backend.dto.request.ProjectRequest;
import com.careerplatform.backend.dto.response.ProjectResponse;
import com.careerplatform.backend.entity.Project;
import com.careerplatform.backend.entity.StudentProfile;
import com.careerplatform.backend.exception.ResourceNotFoundException;
import com.careerplatform.backend.repository.ProjectRepository;
import com.careerplatform.backend.repository.StudentProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final StudentProfileRepository studentProfileRepository;

    @Transactional(readOnly = true)
    public List<ProjectResponse> getMyProjects(Long userId) {
        StudentProfile student = studentProfile(userId);
        return projectRepository.findByStudentId(student.getId()).stream()
                .map(ProjectResponse::fromEntity).toList();
    }

    @Transactional
    public ProjectResponse add(Long userId, ProjectRequest request) {
        StudentProfile student = studentProfile(userId);
        Project project = Project.builder()
                .student(student)
                .title(request.getTitle())
                .description(request.getDescription())
                .technologies(request.getTechnologies())
                .projectUrl(request.getProjectUrl())
                .repoUrl(request.getRepoUrl())
                .build();
        return ProjectResponse.fromEntity(projectRepository.save(project));
    }

    @Transactional
    public ProjectResponse update(Long userId, Long id, ProjectRequest request) {
        StudentProfile student = studentProfile(userId);
        Project project = projectRepository.findByIdAndStudentId(id, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found for this account"));

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setTechnologies(request.getTechnologies());
        project.setProjectUrl(request.getProjectUrl());
        project.setRepoUrl(request.getRepoUrl());

        return ProjectResponse.fromEntity(projectRepository.save(project));
    }

    @Transactional
    public void delete(Long userId, Long id) {
        StudentProfile student = studentProfile(userId);
        Project project = projectRepository.findByIdAndStudentId(id, student.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found for this account"));
        projectRepository.delete(project);
    }

    private StudentProfile studentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}