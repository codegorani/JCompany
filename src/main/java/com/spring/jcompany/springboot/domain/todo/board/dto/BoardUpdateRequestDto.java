package com.spring.jcompany.springboot.domain.todo.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardUpdateRequestDto {
    private Long id;
    private String title;

    @Builder
    public BoardUpdateRequestDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
