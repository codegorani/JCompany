package com.spring.jcompany.springboot.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserForgotPasswordRequestDto {
    private String email;
    private String answer;

    @Builder
    public UserForgotPasswordRequestDto(String email, String answer) {
        this.email = email;
        this.answer = answer;
    }
}
