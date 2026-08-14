package com.careerplatform.backend.repository;

import com.careerplatform.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStudentId(Long studentId);
    List<Application> findByInternshipId(Long internshipId);
    Optional<Application> findByIdAndStudentId(Long id, Long studentId);
    boolean existsByStudentIdAndInternshipId(Long studentId, Long internshipId);
}