package com.careerplatform.backend.repository;

import com.careerplatform.backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByStudentId(Long studentId);
    Optional<Project> findByIdAndStudentId(Long id, Long studentId);
}