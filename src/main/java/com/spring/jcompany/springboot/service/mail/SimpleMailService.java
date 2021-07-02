package com.spring.jcompany.springboot.service.mail;

import com.spring.jcompany.springboot.domain.mail.MailDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class SimpleMailService {
    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "hjhearts21@gmail.com";

    public void mailSend(MailDto mailDto) {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"UTF-8");

                message.setTo(mailDto.getAddress());
                message.setFrom(FROM_ADDRESS);	//env.getProperty("spring.mail.username")
                message.setSubject(mailDto.getTitle());
                message.setText(mailDto.getMessage(), true);
            }
        };

        try{
            mailSender.send(preparator);
        } catch (MailException e){
            e.printStackTrace();
        }
    }
}
