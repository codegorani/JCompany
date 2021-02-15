package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.domain.user.Role;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import com.spring.jcompany.springboot.domain.user.dto.AdminRequestUserUpdateRequestDto;
import com.spring.jcompany.springboot.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/admin/member/{id}")
    public String memberInfoManagementPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", adminService.adminFindUserByIdService(id));
        return "admin/admin-member-management";
    }

    @PostMapping("/admin/member/{id}")
    public String memberUpdateRequestControl(@PathVariable("id") Long id, @RequestParam UserTeam userTeam, @RequestParam UserLevel userLevel,
                                             @RequestParam String name, @RequestParam Role role, @RequestPart MultipartFile picture) throws IOException {
        adminService.adminUserUpdateService(
                AdminRequestUserUpdateRequestDto.builder().id(id).userTeam(userTeam).userLevel(userLevel)
                        .name(name).role(role).picture(picture).build()
        );
        return "redirect:/admin/member";
    }
}
