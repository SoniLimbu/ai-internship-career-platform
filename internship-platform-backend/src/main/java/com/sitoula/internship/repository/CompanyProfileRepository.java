package com.sitoula.internship.repository;

import com.sitoula.internship.entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {

    Optional<CompanyProfile> findByUserId(Long userId);

    Optional<CompanyProfile> findByUserUsername(String username);
}
