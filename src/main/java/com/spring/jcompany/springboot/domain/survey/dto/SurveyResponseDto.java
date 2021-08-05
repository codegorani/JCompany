package com.spring.jcompany.springboot.domain.survey.dto;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.survey.SurveyStatus;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurveyResponseDto {
    private Long id;
    private String surveyName;
    private String surveyDetails;
    private int agreeCount;
    private int disagreeCount;
    private String agreeRate;
    private String disagreeRate;
    private User draftee;
    private SurveyStatus status;

    public SurveyResponseDto(Survey entity) {
        this.id = entity.getId();
        this.surveyName = entity.getSurveyName();
        this.surveyDetails = entity.getSurveyDetails();
        this.agreeCount = entity.getAgreeCount();
        this.disagreeCount = entity.getDisagreeCount();
        if ((entity.getAgreeCount() + entity.getDisagreeCount()) == 0) {
            agreeRate = "0.0";
            disagreeRate = "0.0";
        } else {
            this.agreeRate = String.format("%.2f",((double) entity.getAgreeCount() / (entity.getAgreeCount() + entity.getDisagreeCount()) * 100));
            this.disagreeRate = String.format("%.2f" ,((double) entity.getDisagreeCount() / (entity.getAgreeCount() + entity.getDisagreeCount()) * 100));
        }
        this.draftee = entity.getDraftee();
        this.status = entity.getStatus();
    }
}
