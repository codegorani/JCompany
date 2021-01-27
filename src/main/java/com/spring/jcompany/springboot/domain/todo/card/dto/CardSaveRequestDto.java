package com.spring.jcompany.springboot.domain.todo.card.dto;

import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.todo.card.CardType;
import lombok.Getter;

@Getter
public class CardSaveRequestDto {
    private CardType cardType;
    private String title;
    private String content;
    private Long boardId;

    public CardSaveRequestDto(CardType  cardType, String title, String content, Long boardId) {
        this.cardType = cardType;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
    }
}
