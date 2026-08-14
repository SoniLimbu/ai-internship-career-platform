package com.careerplatform.backend.repository;

import com.careerplatform.backend.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findByStudentId(Long studentId);
    Optional<Certification> findByIdAndStudentId(Long id, Long studentId);
}