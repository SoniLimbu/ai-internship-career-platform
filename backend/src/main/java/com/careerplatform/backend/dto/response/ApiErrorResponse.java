package com.careerplatform.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponse {

    private final String status = "error";
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final List<String> errors;

    public ApiErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorResponse(String message) {
        this(message, null);
    }
}