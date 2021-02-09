package com.spring.jcompany.springboot.web.api.user;

import com.spring.jcompany.springboot.domain.user.dto.UserSaveRequestDto;
import com.spring.jcompany.springboot.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
}
