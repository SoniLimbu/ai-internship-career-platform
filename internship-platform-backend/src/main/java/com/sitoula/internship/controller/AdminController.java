package com.sitoula.internship.controller;

import com.sitoula.internship.dto.response.AdminDashboardResponse;
import com.sitoula.internship.dto.response.ApiResponse;
import com.sitoula.internship.entity.CompanyProfile;
import com.sitoula.internship.entity.User;
import com.sitoula.internship.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dashboard")
    public ApiResponse<AdminDashboardResponse> getDashboard() {
        return ApiResponse.success(adminService.getDashboardStats());
    }

    @GetMapping("/users")
    public ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.success(adminService.getAllUsers());
    }

    @PatchMapping("/users/{id}/verify")
    public ApiResponse<CompanyProfile> verifyCompany(@PathVariable Long id) {
        CompanyProfile profile = adminService.verifyCompany(id);
        return ApiResponse.success("Company verified successfully", profile);
    }
}
