package com.spring.jcompany.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {
    @GetMapping("/mail")
    public String mailPage() {
        return "menu/mail/mail";
    }
}
