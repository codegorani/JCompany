package com.spring.jcompany.springboot.domain.user;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import com.spring.jcompany.springboot.domain.docs.Documents;
import com.spring.jcompany.springboot.domain.todo.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String picture;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birth;

    @Column
    private String question;

    @Column
    private String answer;

    @Column
    @Enumerated(EnumType.STRING)
    private UserTeam userTeam;

    @Column
    @Enumerated(EnumType.STRING)
    private UserLevel userLevel;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private GroupCompany groupCompany;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boardList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Documents> documentsList;

    @OneToMany(mappedBy = "approval")
    private List<Documents> approvalList;

    @Column
    private LocalDateTime lastLoginTime;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column
    private String tempCode;

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL)
    private List<Bulletin> bulletinList;

    @Builder
    public User(Role role, String email, String password, String name, String picture,
                LocalDateTime birth, String question, String answer, UserTeam userTeam,
                UserLevel userLevel, LocalDateTime lastLoginTime, UserStatus status, String tempCode) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        this.question = question;
        this.answer = answer;
        this.userTeam = userTeam;
        this.userLevel = userLevel;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
        this.tempCode = tempCode;
    }

    public User update(String name, String picture, LocalDateTime birth) {
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        return this;
    }

    public User updateByAdmin(UserTeam userTeam, UserLevel userLevel, Role role, String name, String picture) {
        this.userTeam = userTeam;
        this.userLevel = userLevel;
        this.role = role;
        this.name = name;
        this.picture = picture;
        return this;
    }

    public void passwordUpdate(String password) {
        this.password = password;
    }

    public User inactive() {
        this.status = UserStatus.INACTIVE;
        return this;
    }

    public void statusUpdate(UserStatus status) {
        this.status = status;
    }

    public void loggedIn(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setTempCode(String tempCode) {
        this.tempCode = tempCode;
    }

    public User roleUpdate(Role role) {
        this.role = role;
        return this;
    }
}
