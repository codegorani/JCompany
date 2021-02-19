package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DocumentsApprovalUserResponseDto {
    private Long id;
    private String name;
    private UserTeam userTeam;
    private UserLevel userLevel;

    @Builder
    public DocumentsApprovalUserResponseDto(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.userTeam = entity.getUserTeam();
        this.userLevel = entity.getUserLevel();
    }
}
