package com.spring.jcompany.springboot.service.survey;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.survey.SurveyRepository;
import com.spring.jcompany.springboot.domain.survey.SurveyRepositorySupport;
import com.spring.jcompany.springboot.domain.survey.SurveyStatus;
import com.spring.jcompany.springboot.domain.survey.dto.*;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyRepositorySupport surveyRepositorySupport;
    private final UserRepository userRepository;

    @Transactional
    public Long surveySaveService(SurveySaveRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return surveyRepository.save(requestDto.toEntity().setDraftee(user)).getId();
    }

    @Transactional
    public Long surveyProgressService(Long surveyId, Long userId, String type) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));

        if (survey.getStatus().equals(SurveyStatus.WAITING)) {
            return -2L;
        } else if (survey.getStatus().equals(SurveyStatus.DONE)) {
            return -3L;
        }

        String[] totalUserId = survey.getTotalUserId().split(",");
        for (String id : totalUserId) {
            if (id.equals(String.valueOf(userId)))
                return -1L;
        }

        if (type.equals("agree"))
            return surveyRepository.save(survey.agree(userId)).getId();
        else if (type.equals("disagree"))
            return surveyRepository.save(survey.disagree(userId)).getId();
        else
            return -4L;

    }

    @Transactional
    public void surveyDeleteService(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        surveyRepository.delete(survey);
    }

    @Transactional
    public String surveyStatusUpdateService(Long surveyId, SurveyStatus status) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        survey.statusUpdate(status);
        return survey.getStatus().name();
    }

    @Transactional
    public SurveyResponseDto surveyResponseService(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        return new SurveyResponseDto(survey);
    }

    @Transactional
    public List<SurveyListResponseDto> surveyListResponseService() {
        return surveyRepositorySupport.findAllSurvey()
                .stream().map(SurveyListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public SurveyManageResponseDto surveyManageResponseService(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        SurveyManageResponseDto responseDto = new SurveyManageResponseDto(survey);
        String[] agreeUsers = survey.getAgreeUsersId().split(",");
        String[] disagreeUsers = survey.getDisagreeUsersId().split(",");

        for(String s : agreeUsers) {
            Long id = Long.parseLong(s);
            User user = userRepository.findById(id).orElseThrow(null);
            responseDto.addAgreeName(user);
        }
        for(String s : disagreeUsers) {
            Long id = Long.parseLong(s);
            User user = userRepository.findById(id).orElseThrow(null);
            responseDto.addDisagreeName(user);
        }

        return responseDto;
    }

    @Transactional
    public void surveyModifyService(SurveyModifyRequestDto requestDto, Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        surveyRepository.save(survey.surveyModifyWithDto(requestDto));
    }
}
