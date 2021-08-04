package com.spring.jcompany.springboot.domain.survey;

import com.spring.jcompany.springboot.domain.global.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public Survey(String surveyName, String surveyDetails, int agreeCount, int disagreeCount,
                  String agreeUsersId, String disagreeUsersId, String totalUserId, SurveyStatus status) {
        this.surveyName = surveyName;
        this.surveyDetails = surveyDetails;
        this.agreeCount = agreeCount;
        this.disagreeCount = disagreeCount;
        this.agreeUsersId = agreeUsersId;
        this.disagreeUsersId = disagreeUsersId;
        this.totalUserId = totalUserId;
        this.status = status;
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
}
