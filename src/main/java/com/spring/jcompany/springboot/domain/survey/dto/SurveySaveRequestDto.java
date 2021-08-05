package com.spring.jcompany.springboot.domain.survey.dto;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.survey.SurveyStatus;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SurveySaveRequestDto {
    private String surveyName;
    private String surveyDetails;

    @Builder
    public SurveySaveRequestDto(String surveyName, String surveyDetails) {
        this.surveyName = surveyName;
        this.surveyDetails = surveyDetails;
    }

    public Survey toEntity() {
        return Survey.builder()
                .surveyName(surveyName)
                .surveyDetails(surveyDetails)
                .agreeUsersId("")
                .disagreeUsersId("")
                .agreeCount(0)
                .disagreeCount(0)
                .totalUserId("")
                .status(SurveyStatus.WAITING)
                .build();
    }
}
