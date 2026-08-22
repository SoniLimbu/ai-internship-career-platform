package com.sitoula.internship.service;

import com.sitoula.internship.entity.Internship;
import com.sitoula.internship.entity.Skill;
import com.sitoula.internship.entity.StudentProfile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchScoreService {

    /**
     * Calculates the percentage overlap between a student's skills and an
     * internship's required skills. Comparison is case-insensitive on skill name.
     *
     * @return a percentage between 0.0 and 100.0. Returns 0.0 if the internship
     *         has no required skills defined (nothing to match against).
     */
    public double calculateMatchScore(StudentProfile student, Internship internship) {
        Set<String> requiredSkillNames = internship.getRequiredSkills().stream()
                .map(Skill::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        if (requiredSkillNames.isEmpty()) {
            return 0.0;
        }

        Set<String> studentSkillNames = student.getSkills().stream()
                .map(Skill::getName)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        long matchedCount = requiredSkillNames.stream()
                .filter(studentSkillNames::contains)
                .count();

        double score = (matchedCount / (double) requiredSkillNames.size()) * 100.0;
        return Math.round(score * 100.0) / 100.0; // round to 2 decimal places
    }
}
