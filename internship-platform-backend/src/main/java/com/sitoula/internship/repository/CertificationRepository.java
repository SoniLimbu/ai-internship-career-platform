package com.sitoula.internship.repository;

import com.sitoula.internship.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    List<Certification> findByStudentProfileId(Long studentProfileId);
}
