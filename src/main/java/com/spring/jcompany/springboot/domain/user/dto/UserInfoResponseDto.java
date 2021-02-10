package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.Role;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserInfoResponseDto {
    private Long id;
    private Role role;
    private String email;
    private String name;
    private String picture;
    private LocalDateTime birth;
    private UserTeam userTeam;
    private UserLevel userLevel;

    public UserInfoResponseDto(User entity) {
        this.id = entity.getId();
        this.role = entity.getRole();
        this.email = entity.getEmail();
        this.name = entity.getName();
        String classpathUrl = entity.getPicture().replace("\\", "/");
        String baseUrl = "C:/Users/USER/Documents/GitHub/JCompany/src/main/resources/static";
        this.picture = classpathUrl.replace(baseUrl, "");
        this.birth = entity.getBirth();
        this.userTeam = entity.getUserTeam();
        this.userLevel = entity.getUserLevel();
    }
}
