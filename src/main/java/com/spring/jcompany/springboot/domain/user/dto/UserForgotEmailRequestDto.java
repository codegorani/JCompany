package com.spring.jcompany.springboot.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserForgotEmailRequestDto {
    private String name;
    private String birth;

    @Builder
    public UserForgotEmailRequestDto(String name, String birth) {
        this.name = name;
        this.birth = birth;
    }
}
