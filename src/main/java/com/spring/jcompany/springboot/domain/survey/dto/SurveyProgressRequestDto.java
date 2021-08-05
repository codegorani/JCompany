package com.spring.jcompany.springboot.domain.survey.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SurveyProgressRequestDto {
    private Long surveyId;
    private Long userId;
    private String type;

    @Builder
    public SurveyProgressRequestDto(Long surveyId, Long userId, String type) {
        this.surveyId = surveyId;
        this.userId = userId;
        this.type = type;
    }
}
