package com.spring.jcompany.springboot.web;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, RedirectAttributes attributes) {
        if(user != null) {
            model.addAttribute("loginUser", user);
            if(user.isDormant()) {
                userService.sendRandomPassword(user.getId());
                return "redirect:/logout/dormant";
            }
        }

        return "index";
    }

}
