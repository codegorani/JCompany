package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.domain.user.UserTeam;
import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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



    @GetMapping("/menu/user/{id}")
    public String userInfoPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("loginUser", userService.userInfoService(id));
        return "menu/user/user-info";
    }

    @GetMapping("/login/error")
    public String loginFailure(Model model) {
        model.addAttribute("isError", true);
        return "menu/login/login";
    }

    @PostMapping("/signup")
    public String userSaveRequestControl(@RequestParam String email, @RequestParam String password,
                                         @RequestParam String name, @RequestParam String birth, @RequestParam String question,
                                         @RequestParam String answer, @RequestPart MultipartFile picture, @RequestParam UserTeam userTeam, Model model) throws Exception {
        String baseUrl = "C:\\Users\\USER\\Documents\\GitHub\\JCompany\\src\\main\\resources\\static\\images\\user\\";
        String userDir = email + birth;
        String storedUrl = baseUrl + userDir;
        String filePath = "none";
        if (!Objects.equals(picture.getOriginalFilename(), "")) {
            File pathAsFile = new File(storedUrl);
            if (!Files.exists(Paths.get(storedUrl))) {
                pathAsFile.mkdir();
            }
            filePath = storedUrl + "\\" + picture.getOriginalFilename();
            picture.transferTo(new File(filePath));
        }
        UserSaveRequestDto requestDto = UserSaveRequestDto.builder()
                .email(email).password(password).name(name)
                .birth(LocalDateTime.of(Integer.parseInt(birth.substring(0, 4)),
                        Integer.parseInt(birth.substring(4, 6)),
                        Integer.parseInt(birth.substring(6)), 0, 0, 0))
                .question(question).answer(answer).userTeam(userTeam).picture(filePath).build();
        userService.userSaveService(requestDto);
        return "redirect:/";
    }

    @GetMapping("/menu/user/pw/{id}")
    public String userPasswordChangePage(@PathVariable("id") Long id) {
        return "menu/user/user-password";
    }
}
