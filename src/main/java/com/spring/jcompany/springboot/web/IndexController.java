package com.spring.jcompany.springboot.web;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.user.UserStatus;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, RedirectAttributes attributes,
                        HttpServletRequest request, HttpServletResponse response) {
        if(user != null) {
            model.addAttribute("loginUser", user);
            if(user.getStatus().equals(UserStatus.INACTIVE)) {
                userService.sendRandomPassword(user.getId());
                return "redirect:/logout/dormant";
            } else if(user.getStatus().equals(UserStatus.WAITING)) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                return "redirect:/inactive/reset/" + user.getId();
            }
        }

        return "index";
    }

}
