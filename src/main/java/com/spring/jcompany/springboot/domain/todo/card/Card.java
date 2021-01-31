package com.spring.jcompany.springboot.domain.todo.card;

import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import com.spring.jcompany.springboot.domain.todo.board.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Card extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CardType cardType;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn
    private Board board;

    @Builder
    public Card(CardType cardType, String title, String content, Board board) {
        this.cardType = cardType;
        this.title = title;
        this.content = content;
        this.board = board;
    }

    public void update(CardType cardType, String title, String content) {
        this.cardType = cardType;
        this.title = title;
        this.content = content;
    }

    public void nextProcess(CardType cardType) {
        this.cardType = cardType;
    }
}
