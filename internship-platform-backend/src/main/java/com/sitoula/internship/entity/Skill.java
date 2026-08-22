package com.sitoula.internship.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills", uniqueConstraints = {
        @UniqueConstraint(name = "uk_skills_name", columnNames = "name")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "skills")
    private Set<StudentProfile> studentProfiles = new HashSet<>();

    @Builder.Default
    @ManyToMany(mappedBy = "requiredSkills")
    private Set<Internship> internships = new HashSet<>();
}
