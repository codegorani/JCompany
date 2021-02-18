package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ManagerRequestUserListResponseDto {
    private Long id;
    private String name;
    private String email;
    private UserLevel userLevel;
    private LocalDateTime birth;

    @Builder
    public ManagerRequestUserListResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.userLevel = entity.getUserLevel();
        this.birth = entity.getBirth();
    }
}
