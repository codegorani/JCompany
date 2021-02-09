package com.spring.jcompany.springboot.web.api.admin;

import com.spring.jcompany.springboot.domain.mail.MailDto;
import com.spring.jcompany.springboot.service.admin.AdminService;
import com.spring.jcompany.springboot.service.mail.SimpleMailService;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminApiController {
    private final UserService userService;
    private final AdminService adminService;

    @DeleteMapping("/admin/member/{id}")
    public void adminUserDeleteRequestControl(@PathVariable("id") Long id) {
        adminService.adminUserDeleteService(id);
    }

    @PutMapping("/admin/member/{id}")
    public Long adminUserPasswordResetRequestControl(@PathVariable("id") Long id) {
        return adminService.adminUserPasswordResetService(id);
    }
}
