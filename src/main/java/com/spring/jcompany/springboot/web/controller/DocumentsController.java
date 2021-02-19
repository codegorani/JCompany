package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.docs.DocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class DocumentsController {

    private final DocumentsService documentsService;

    @GetMapping("/documents")
    public String documentsPage() {
        return "menu/documents/documents-menu";
    }

    @GetMapping("/documents/write")
    public String documentsWritePage(@LoginUser SessionUser user, Model model) {
        model.addAttribute("approvals", documentsService.documentsApprovalListService(user));
        return "menu/documents/documents-write";
    }


}
