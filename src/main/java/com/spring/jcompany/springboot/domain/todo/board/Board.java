package com.spring.jcompany.springboot.domain.todo.board;

import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Card> cardList;

    @Builder
    public Board(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void update(String title) {
        this.title = title;
    }
}
