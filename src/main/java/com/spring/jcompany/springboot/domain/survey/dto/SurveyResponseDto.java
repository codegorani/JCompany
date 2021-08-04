package com.spring.jcompany.springboot.domain.survey.dto;

import com.spring.jcompany.springboot.domain.survey.Survey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyResponseDto {
    private String surveyName;
    private String surveyDetails;
    private int agreeCount;
    private int disagreeCount;
    private double agreeRate;
    private double disagreeRate;

    public SurveyResponseDto(Survey entity) {
        this.surveyName = entity.getSurveyName();
        this.surveyDetails = entity.getSurveyDetails();
        this.agreeCount = entity.getAgreeCount();
        this.disagreeCount = entity.getDisagreeCount();
        this.agreeRate = (double)(entity.getAgreeCount() / (entity.getAgreeCount() + entity.getDisagreeCount())) * 100;
        this.disagreeRate = (double)(entity.getDisagreeCount() / (entity.getAgreeCount() + entity.getDisagreeCount())) * 100;
    }
}
