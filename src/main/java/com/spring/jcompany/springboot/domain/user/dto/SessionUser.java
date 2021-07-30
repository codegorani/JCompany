package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String email;
    private String name;
    private LocalDateTime birth;
    private Role role;
    private UserTeam userTeam;
    private UserLevel userLevel;
    private UserStatus status;

    public SessionUser(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.birth = entity.getBirth();
        this.role = entity.getRole();
        this.userTeam = entity.getUserTeam();
        this.userLevel = entity.getUserLevel();
        this.status = entity.getStatus();
    }
}
