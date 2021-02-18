package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.manager.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ManagerController {

    private final ManagerService managerService;

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_ADMIN', 'ROLE_USER_MANAGER')")
    @GetMapping("/manager")
    public String managerMenuPage() {
        return "menu/manager/manager-menu";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER', 'ROLE_USER_MANAGER')")
    @GetMapping("/manager/user")
    public String managerUserPage(@LoginUser SessionUser user, Model model) {
        model.addAttribute("teams", managerService.findAllMyTeamMemberService(user.getUserTeam()));
        return "menu/manager/manager-user";
    }
}
