package com.spring.jcompany.springboot.domain.todo.card.dto;

import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.todo.card.CardType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CardBoardResponseDto {
    private Long id;
    private CardType cardType;
    private String title;

    @Builder
    public CardBoardResponseDto(Card entity) {
        this.id = entity.getId();
        this.cardType = entity.getCardType();
        this.title = entity.getTitle();
    }
}
