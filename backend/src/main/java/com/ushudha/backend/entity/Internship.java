package com.ushudha.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "internships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false)
    private String title;

    @Column(length = 4000)
    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    private WorkMode workMode; // REMOTE, ONSITE, HYBRID

    @Enumerated(EnumType.STRING)
    private InternshipType type; // FULL_TIME, PART_TIME

    private Double stipend;

    private LocalDate applicationDeadline;

    /** Comma-separated tags used for search/filter, e.g. "React, SQL, AI" */
    private String requiredSkills;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public enum WorkMode { REMOTE, ONSITE, HYBRID }
    public enum InternshipType { FULL_TIME, PART_TIME }
}