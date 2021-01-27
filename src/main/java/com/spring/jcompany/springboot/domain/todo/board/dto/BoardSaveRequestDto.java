package com.spring.jcompany.springboot.domain.todo.board.dto;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardSaveRequestDto {
    private String title;
    private String author;
    public BoardSaveRequestDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Board toEntity() {
        return Board.builder().title(title).author(author).build();
    }
}
