package com.sitoula.internship.repository;

import com.sitoula.internship.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStudentId(Long studentId);

    List<Application> findByInternshipId(Long internshipId);

    Optional<Application> findByStudentIdAndInternshipId(Long studentId, Long internshipId);

    boolean existsByStudentIdAndInternshipId(Long studentId, Long internshipId);

    long countByInternshipId(Long internshipId);
}
