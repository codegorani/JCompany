package com.spring.jcompany.springboot.domain.survey.dto;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SurveyManageResponseDto {

    private String surveyName;
    private String surveyDetails;
    private int agreeCount;
    private int disagreeCount;
    private List<String> agreeUserName;
    private List<String> disagreeUserName;

    public SurveyManageResponseDto(Survey entity) {
        this.surveyName = entity.getSurveyName();
        this.surveyDetails = entity.getSurveyDetails();
        this.agreeCount = entity.getAgreeCount();
        this.disagreeCount = entity.getDisagreeCount();
        this.agreeUserName = new ArrayList<>();
        this.disagreeUserName = new ArrayList<>();
    }

    public void addAgreeName(User user) {
        this.agreeUserName.add(user.getName());
    }

    public void addDisagreeName(User user) {
        this.disagreeUserName.add(user.getName());
    }
}
