package com.spring.jcompany.springboot.domain.todo.board.dto;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;
    private User user;

    @Builder
    public BoardListResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
        this.user = entity.getUser();
    }
}
