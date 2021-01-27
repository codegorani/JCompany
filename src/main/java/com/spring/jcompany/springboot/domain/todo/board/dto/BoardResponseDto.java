package com.spring.jcompany.springboot.domain.todo.board.dto;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.todo.card.dto.CardBoardResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BoardResponseDto {
    private Long id;
    private String title;
    private List<CardBoardResponseDto> cardList;

    @Builder
    public BoardResponseDto(Board boardEntity, List<CardBoardResponseDto> cardList) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.cardList = cardList;
    }
}
