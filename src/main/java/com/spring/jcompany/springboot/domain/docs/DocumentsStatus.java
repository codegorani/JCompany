package com.spring.jcompany.springboot.domain.docs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentsStatus {
    WAITING("WAITING", "대기"),
    READING("READING", "열람"),
    APPROVAL_DONE("APPROVAL_DONE", "결재완료"),
    COMPANION("COMPANION", "반려");

    private final String key;
    private final String status;
}
