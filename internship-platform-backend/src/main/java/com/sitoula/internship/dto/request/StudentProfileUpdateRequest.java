package com.sitoula.internship.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentProfileUpdateRequest {

    @Size(max = 200)
    private String university;

    @Size(max = 150)
    private String degree;

    private String bio;
}
