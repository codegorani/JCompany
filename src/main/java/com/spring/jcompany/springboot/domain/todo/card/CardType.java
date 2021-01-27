package com.spring.jcompany.springboot.domain.todo.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardType {
    STANDBY("STANDBY", "대기"),
    PROGRESS("PROGRESS", "진행"),
    COMPLETE("COMPLETE", "완료");

    private final String key;
    private final String title;
}
