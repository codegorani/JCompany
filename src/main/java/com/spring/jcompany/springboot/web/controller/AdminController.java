package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/admin/member")
    public String memberManagementPage(Model model) {
        model.addAttribute("users", adminService.adminFindAllUserService());
        return "admin/admin-member";
    }

    @GetMapping("/admin/board")
    public String boardManagementPage(Model model) {
        model.addAttribute("boards", adminService.adminFindAllBoardService());
        return "admin/admin-board";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPage() {
        return "admin/admin-menu";
    }
}
