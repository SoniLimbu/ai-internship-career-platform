package com.careerplatform.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final String status = "success";
    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final T data;

    private ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(String message, T data) {
        return new ApiResponse<>(message, data);
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(null, data);
    }
}