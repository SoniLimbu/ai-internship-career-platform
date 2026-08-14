package com.careerplatform.backend.dto.response;

import com.careerplatform.backend.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ResumeResponse {
    private Long id;
    private String originalFileName;
    private String contentType;
    private Long fileSizeBytes;
    private LocalDateTime uploadedAt;
    private String downloadUrl;

    public static ResumeResponse fromEntity(Resume r) {
        return new ResumeResponse(r.getId(), r.getOriginalFileName(), r.getContentType(),
                r.getFileSizeBytes(), r.getUploadedAt(), "/api/resume/download");
    }
}