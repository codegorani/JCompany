package com.spring.jcompany.springboot.web.api.mail;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.mail.MailDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.service.mail.SimpleMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MailApiController {
    private final SimpleMailService mailService;

    @PostMapping("/api/v1/mail")
    public String sendMail(@RequestBody MailDto mailDto, @LoginUser User user) {
        try {
            mailService.mailSend(mailDto);
        } catch(MailSendException e) {
            return "-1";
        }
        return "0";
    }
}
