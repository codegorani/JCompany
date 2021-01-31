package com.spring.jcompany.springboot.domain.todo.card.dto;

import com.spring.jcompany.springboot.domain.todo.card.CardType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CardUpdateRequestDto {
    private String title;
    private String content;
    private CardType cardType;

    @Builder
    public CardUpdateRequestDto(String title, String content, CardType cardType) {
        this.title = title;
        this.content = content;
        this.cardType = cardType;
    }
}
