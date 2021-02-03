package com.spring.jcompany.springboot.domain.todo.board.dto;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardSaveRequestDto {
    private String title;
    private String author;
    private Long userId;

    public BoardSaveRequestDto(String title, String author, Long userId) {
        this.title = title;
        this.author = author;
        this.userId = userId;
    }

    public Board toEntity() {
        return Board.builder().title(title).author(author).build();
    }
}
