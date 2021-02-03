package com.spring.jcompany.springboot.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminController {



    @GetMapping("/admin/member")
    public String memberManagementPage(Model model) {
        return "admin/admin-member";
    }
}
