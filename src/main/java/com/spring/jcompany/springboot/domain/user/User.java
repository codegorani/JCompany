package com.spring.jcompany.springboot.domain.user;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boardList;

    @Builder
    public User(Role role, String email, String password, String name, String picture,
                LocalDateTime birth, String question, String answer) {
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        this.question = question;
        this.answer = answer;
    }

    public User update(String name, String picture, LocalDateTime birth) {
        this.name = name;
        this.picture = picture;
        this.birth = birth;
        return this;
    }

    public void passwordUpdate(String password, String question, String answer) {
        this.password = password;
        this.question = question;
        this.answer = answer;
    }
}
