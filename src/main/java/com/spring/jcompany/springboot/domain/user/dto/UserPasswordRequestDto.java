package com.spring.jcompany.springboot.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPasswordRequestDto {
    private Long id;
    private String password;
    private String newPassword;

    @Builder
    public UserPasswordRequestDto(Long id, String password, String newPassword) {
        this.id = id;
        this.password = password;
        this.newPassword = newPassword;
    }
}
