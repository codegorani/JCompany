package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/survey/home")
    public String gotoSurveyPage(Model model, @LoginUser SessionUser user) {
        model.addAttribute("surveyList", surveyService.surveyListResponseService());
        model.addAttribute("loginUser", user);
        return "menu/survey/survey-home";
    }

    @GetMapping("/survey/{id}")
    public String surveyDetailPage(Model model, @PathVariable("id") Long surveyId, @LoginUser SessionUser user) {
        model.addAttribute("survey", surveyService.surveyResponseService(surveyId));
        model.addAttribute("loginUser", user);
        return "menu/survey/survey-view";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    @GetMapping("/survey/create")
    public String surveyCreate() {
        return "menu/survey/survey-create";
    }

    @GetMapping("/survey/manage/{id}")
    public String surveyManagePage(@PathVariable("id") Long surveyId, Model model) {
        model.addAttribute("survey", surveyService.surveyManageResponseService(surveyId));
        return "menu/survey/survey-management";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    @GetMapping("/survey/create")
    public String surveyCreatePage(@LoginUser SessionUser user, Model model) {
        model.addAttribute("loginUser", user);
        return "menu/survey/survey-create";
    }
}
