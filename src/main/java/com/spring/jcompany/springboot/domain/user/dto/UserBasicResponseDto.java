package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserBasicResponseDto {
    private String email;
    private String name;
    private LocalDateTime birth;

    @Builder
    public UserBasicResponseDto(User entity) {
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.birth = entity.getBirth();
    }
}
