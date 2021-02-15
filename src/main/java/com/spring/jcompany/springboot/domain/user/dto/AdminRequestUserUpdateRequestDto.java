package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.Role;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AdminRequestUserUpdateRequestDto {
    private Long id;
    private UserTeam userTeam;
    private UserLevel userLevel;
    private String name;
    private Role role;
    private MultipartFile picture;

    @Builder
    public AdminRequestUserUpdateRequestDto(Long id, UserTeam userTeam, UserLevel userLevel,
                                            String name, Role role, MultipartFile picture) {
        this.id = id;
        this.userTeam = userTeam;
        this.userLevel = userLevel;
        this.name = name;
        this.role = role;
        this.picture = picture;
    }
}
