package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "menu/login/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "menu/login/signup";
    }

    @PostMapping("/signup")
    public String userSaveRequestControl(@RequestParam String email, @RequestParam String password,
                                         @RequestParam String name, @RequestParam String birth, @RequestParam String question,
                                         @RequestParam String answer, @RequestPart MultipartFile picture, Model model) throws Exception{
        String baseUrl = "C:\\Users\\USER\\Documents\\GitHub\\jcompanyfile\\";
        String filePath = "none";
        if(!Objects.equals(picture.getOriginalFilename(), "")) {
            filePath = baseUrl + picture.getOriginalFilename();
            picture.transferTo(new File(filePath));
        }
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email).password(password).name(name)
                .birth(LocalDateTime.of(Integer.parseInt(birth.substring(0, 4)),
                        Integer.parseInt(birth.substring(4, 6)),
                        Integer.parseInt(birth.substring(6)), 0, 0, 0))
                .question(question).answer(answer).picture(filePath).build();
        userService.userSaveService(requestDto);
        return "index";
    }

}
