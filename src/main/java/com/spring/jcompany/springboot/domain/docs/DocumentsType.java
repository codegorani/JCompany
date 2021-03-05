package com.spring.jcompany.springboot.domain.docs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentsType {
    NOTICE("NOTICE", "공지"),
    BUSINESS_DRAFT("BUSINESS_DRAFT", "업무기안"),
    VACATION("VACATION", "휴가계"),
    APPLICATION_PROOF("APPLICATION_PROOF", "증명신청서"),
    LEAVE_ABSENCE("LEAVE_ABSENCE", "휴직계"),
    RETURNING("RETURNING", "복직계"),
    RETIREMENT("RETIREMENT", "퇴직계"),
    REASON_ABSENCE("REASON_ABSENCE", "결근사유서"),
    BUSINESS_TRIP("BUSINESS_TRIP", "출장신청서"),
    EXPENDITURE_RESOLUTION("EXPENDITURE_RESOLUTION", "지출결의서");

    private final String key;
    private final String type;

}
