package com.spring.jcompany.springboot.domain.survey.dto;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.survey.SurveyStatus;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;

@Getter
public class SurveyListResponseDto {
    private Long id;
    private String surveyName;
    private String surveyDetails;
    private SurveyStatus status;
    private User draftee;

    public SurveyListResponseDto(Survey entity) {
        this.id = entity.getId();
        this.surveyName = entity.getSurveyName();
        this.surveyDetails = entity.getSurveyDetails();
        this.status = entity.getStatus();
        this.draftee = entity.getDraftee();
    }

}
