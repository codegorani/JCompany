package com.spring.jcompany.springboot.domain.todo.card.dto;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.todo.card.CardType;
import lombok.Getter;

@Getter
public class CardResponseDto {
    private Long id;
    private String title;
    private String content;
    private CardType cardType;
    private Board board;

    public CardResponseDto(Card entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.cardType = entity.getCardType();
        this.board = entity.getBoard();
    }
}
