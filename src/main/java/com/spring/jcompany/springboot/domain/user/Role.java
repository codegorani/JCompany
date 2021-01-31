package com.spring.jcompany.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    DEVELOPER("ROLE_DEVELOPER", "개발자");

    private final String key;
    private final String title;
}
