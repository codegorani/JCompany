package com.spring.jcompany.springboot.domain.user.dto;

import com.spring.jcompany.springboot.domain.user.Role;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserLevel;
import com.spring.jcompany.springboot.domain.user.UserTeam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class UserSaveRequestDto {

    private String email;
    @Setter
    private String password;
    private String name;
    @Setter
    private String picture;
    private LocalDateTime birth;
    private String question;
    private String answer;
    private UserTeam userTeam;

    @Builder
    public UserSaveRequestDto(String email, String password, String name, String picture,
                              LocalDateTime birth, String question, String answer, UserTeam userTeam) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        this.question = question;
        this.answer = answer;
        this.userTeam = userTeam;
    }

    public User toEntity() {
        return User.builder()
                .role(Role.USER)
                .email(email)
                .password(password)
                .name(name)
                .picture(picture)
                .birth(birth)
                .question(question)
                .answer(answer)
                .userTeam(userTeam)
                .userLevel(UserLevel.INTERN)
                .build();
    }
}
