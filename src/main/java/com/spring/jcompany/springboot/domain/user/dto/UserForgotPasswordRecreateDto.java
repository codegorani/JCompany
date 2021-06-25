package com.spring.jcompany.springboot.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserForgotPasswordRecreateDto {
    private String email;
    private String password;

    @Builder
    public UserForgotPasswordRecreateDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
