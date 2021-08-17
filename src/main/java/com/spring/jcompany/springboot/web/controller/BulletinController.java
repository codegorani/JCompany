package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.bulletin.BulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BulletinController {

    private final BulletinService bulletinService;

    @GetMapping("/bulletin/home")
    public String toBulletinHome(Model model, @LoginUser SessionUser user) {
        model.addAttribute("bulletinList", bulletinService.bulletinListResponseDto(user.getId()));
        return "menu/bulletin/bulletin-home";
    }

    @GetMapping("/bulletin/{id}")
    public String bulletinViewPage(@PathVariable("id") Long bulletinId, @LoginUser SessionUser user, Model model) {
        model.addAttribute("bulletin", bulletinService.bulletinInfoResponseService(bulletinId, user.getId()));
        return "menu/bulletin/bulletin-view";
    }
}
