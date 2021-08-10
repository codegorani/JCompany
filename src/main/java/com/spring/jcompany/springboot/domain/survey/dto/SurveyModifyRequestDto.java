package com.spring.jcompany.springboot.domain.survey.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SurveyModifyRequestDto {
    private String surveyName;
    private String surveyDetail;

    @Builder
    public SurveyModifyRequestDto(String surveyName, String surveyDetail) {
        this.surveyName = surveyName;
        this.surveyDetail = surveyDetail;
    }
}
