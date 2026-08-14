package com.careerplatform.backend.repository;

import com.careerplatform.backend.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByStudentId(Long studentId);
    Optional<Skill> findByIdAndStudentId(Long id, Long studentId);
}