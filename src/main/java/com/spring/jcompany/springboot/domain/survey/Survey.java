package com.spring.jcompany.springboot.domain.survey;

import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import com.spring.jcompany.springboot.domain.survey.dto.SurveyModifyRequestDto;
import com.spring.jcompany.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Survey extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int agreeCount;

    @Column
    private int disagreeCount;

    @Column
    private String surveyName;

    @Column
    private String surveyDetails;

    @Column
    private String agreeUsersId;

    @Column
    private String disagreeUsersId;

    @Column
    private String totalUserId;

    @Column
    @Enumerated(EnumType.STRING)
    private SurveyStatus status;

    @ManyToOne
    private User draftee;

    @Builder
    public Survey(String surveyName, String surveyDetails, int agreeCount, int disagreeCount,
                  String agreeUsersId, String disagreeUsersId, String totalUserId, SurveyStatus status,
                  User draftee) {
        this.surveyName = surveyName;
        this.surveyDetails = surveyDetails;
        this.agreeCount = agreeCount;
        this.disagreeCount = disagreeCount;
        this.agreeUsersId = agreeUsersId;
        this.disagreeUsersId = disagreeUsersId;
        this.totalUserId = totalUserId;
        this.status = status;
        this.draftee = draftee;
    }

    public Survey agree(Long userId) {
        if (totalUserId.equals(""))  {
            totalUserId += userId;
        } else {
            this.totalUserId += "," + userId;
        }
        if (agreeUsersId.equals("")) {
            agreeUsersId += userId;
        } else {
            this.agreeUsersId += "," + userId;
        }
        this.agreeCount++;
        return this;
    }

    public Survey disagree(Long userId) {
        if (totalUserId.equals(""))  {
            totalUserId += userId;
        } else {
            this.totalUserId += "," + userId;
        }
        if (disagreeUsersId.equals("")) {
            disagreeUsersId += userId;
        } else {
            this.disagreeUsersId += "," + userId;
        }
        this.disagreeCount++;
        return this;
    }

    public void statusUpdate(SurveyStatus status) {
        this.status = status;
    }

    public Survey setDraftee(User draftee) {
        this.draftee = draftee;
        return this;
    }

    public Survey surveyModifyWithDto(SurveyModifyRequestDto requestDto) {
        this.surveyName = requestDto.getSurveyName();
        this.surveyDetails = requestDto.getSurveyDetail();
        return this;
    }

    public void agreeUserRemoved() {
        this.agreeCount--;
    }

    public void disagreeUserRemoved() {
        this.disagreeCount--;
    }

    public Survey setAgreeUserId(List<Long> ids) {
        StringBuilder newUserList = new StringBuilder();

        for (Long id : ids) {
            if (newUserList.toString().equals("")) {
                newUserList.append(id);
            } else {
                newUserList.append(",").append(id);
            }
        }

        this.agreeUsersId = newUserList.toString();
        return this;
    }

    public Survey setDisagreeUserId(List<Long> ids) {
        StringBuilder newUserList = new StringBuilder();

        for (Long id : ids) {
            if (newUserList.toString().equals("")) {
                newUserList.append(id);
            } else {
                newUserList.append(",").append(id);
            }
        }

        this.disagreeUsersId = newUserList.toString();
        return this;
    }

    public Survey setTotalUserId(List<Long> ids) {
        StringBuilder newUserList = new StringBuilder();
        for (Long id : ids) {
            if (newUserList.toString().equals("")) {
                newUserList.append(id);
            } else {
                newUserList.append(",").append(id);
            }
        }
        this.totalUserId = newUserList.toString();
        return this;
    }
}
