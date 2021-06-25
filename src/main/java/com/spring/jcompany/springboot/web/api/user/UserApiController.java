package com.spring.jcompany.springboot.web.api.user;

import com.spring.jcompany.springboot.domain.user.UserTeam;
import com.spring.jcompany.springboot.domain.user.dto.*;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @DeleteMapping("/api/v1/user/{id}")
    public void userDeleteRequestControl(@PathVariable("id") Long id) {
        userService.userDeleteService(id);
    }

    @PostMapping("/emailValid")
    public String emailValid(@RequestBody String email) {
        System.out.println(email);
        return userService.isEmailValid(email);
    }

    @PostMapping("/user/password")
    public Long userPasswordChangeControl(@RequestBody UserPasswordRequestDto requestDto) {
        return userService.userPasswordChange(requestDto);
    }

    @PostMapping("/forgot/email/req")
    public ResponseEntity<String> userEmailFindControl(@RequestBody UserForgotEmailRequestDto requestDto) {
        return new ResponseEntity<>(userService.findForgotEmail(requestDto), HttpStatus.OK);
    }

    @PostMapping("/forgot/password/req")
    public ResponseEntity<String> userPasswordFindControl(@RequestBody UserForgotPasswordRequestDto requestDto) {
        return new ResponseEntity<>(userService.findForgotPassword(requestDto), HttpStatus.OK);
    }

    @PostMapping("/forgot/password/recreate")
    public Long userPasswordForgotRecreate(@RequestBody UserForgotPasswordRecreateDto recreateDto) {
        userService.userPasswordChangeAsForgot(recreateDto);
        return 0L;
    }

    @PostMapping("/forgot/password/email")
    public String emailExist(@RequestBody String email) {
        return userService.isEmailExist(email);
    }
}
