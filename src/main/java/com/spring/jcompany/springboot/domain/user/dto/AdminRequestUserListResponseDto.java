package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminRequestUserListResponseDto {
    private Long id;
    private String email;
    private String name;
    private UserTeam userTeam;
    private UserLevel userLevel;
    private LocalDateTime birth;

    @Builder
    public AdminRequestUserListResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.userTeam = entity.getUserTeam();
        this.userLevel = entity.getUserLevel();
        this.birth = entity.getBirth();
    }
}
