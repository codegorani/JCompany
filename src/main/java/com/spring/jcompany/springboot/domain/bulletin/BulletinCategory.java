package com.spring.jcompany.springboot.domain.bulletin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BulletinCategory {
    NOTICE("전사공지"),
    EDUCATION("교육"),
    APPOINTMENT("인사발령"),
    FAMILY_EVENT("경조사"),
    SEMINAR("세미나");

    private final String categoryName;
}
