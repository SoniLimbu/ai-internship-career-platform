package com.careerplatform.backend.dto.request;

import com.careerplatform.backend.entity.Application;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationStatusUpdateRequest {

    @NotNull(message = "Status is required")
    private Application.ApplicationStatus status;
}