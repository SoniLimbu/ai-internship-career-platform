package com.careerplatform.backend.repository;

import com.careerplatform.backend.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findByActiveTrue();

    @Query("""
           SELECT i FROM Internship i
           WHERE i.active = true
           AND (:keyword IS NULL OR LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                OR LOWER(i.requiredSkills) LIKE LOWER(CONCAT('%', :keyword, '%')))
           AND (:location IS NULL OR LOWER(i.location) LIKE LOWER(CONCAT('%', :location, '%')))
           AND (:workMode IS NULL OR i.workMode = :workMode)
           AND (:type IS NULL OR i.type = :type)
           """)
    List<Internship> search(
            @Param("keyword") String keyword,
            @Param("location") String location,
            @Param("workMode") Internship.WorkMode workMode,
            @Param("type") Internship.InternshipType type
    );
}