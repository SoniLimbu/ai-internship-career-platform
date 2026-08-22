package com.sitoula.internship.repository;

import com.sitoula.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long>, JpaSpecificationExecutor<Internship> {

    List<Internship> findByCompanyId(Long companyId);

    List<Internship> findByIsActiveTrue();
}
