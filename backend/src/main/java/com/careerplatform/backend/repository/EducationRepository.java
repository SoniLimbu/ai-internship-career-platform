package com.careerplatform.backend.repository;

import com.careerplatform.backend.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findByStudentId(Long studentId);
    Optional<Education> findByIdAndStudentId(Long id, Long studentId);
}