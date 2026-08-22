package com.sitoula.internship.repository;

import com.sitoula.internship.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    List<Education> findByStudentProfileId(Long studentProfileId);
}
