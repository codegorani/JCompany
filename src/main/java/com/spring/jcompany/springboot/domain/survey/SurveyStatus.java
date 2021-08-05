package com.spring.jcompany.springboot.domain.survey;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SurveyStatus {
    WAITING("설문대기"), 
    PROGRESS("진행중"), 
    DONE("완료");
    
    private final String statusName;
}
