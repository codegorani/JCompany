package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.docs.DocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("loginUser", user);
        return "menu/documents/documents-write";
    }

    @GetMapping("/documents/list/me")
    public String documentsMePage(@LoginUser SessionUser user, Model model) {
        model.addAttribute("loginUser", user);
        model.addAttribute("docs", documentsService.documentsMyDocsListService(user.getId()));
        return "menu/documents/documents-list-me";
    }

    @GetMapping("/documents/list/tome")
    public String documentsToMePage(@LoginUser SessionUser user, Model model) {
        model.addAttribute("loginUser", user);
        model.addAttribute("docs", documentsService.documentsToMeListService(user.getId()));
        return "menu/documents/documents-list-tome";
    }

    @GetMapping("/api/docs/{id}")
    public String documentsDetailToMePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("doc", documentsService.documentsToMeDetailService(id));
        return "menu/documents/documents-view-tome";
    }

    @GetMapping("/api/docs/approval/{id}")
    public String documentsApprovalRequestControl(@PathVariable("id") Long id) {
        documentsService.documentsApprovalService(id);
        return "redirect:/documents/list/tome";
    }
}
