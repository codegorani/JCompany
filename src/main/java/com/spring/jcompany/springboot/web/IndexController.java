package com.spring.jcompany.springboot.web;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("loginUser", user);
        }
        return "index";
    }

}
