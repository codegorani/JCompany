package com.spring.jcompany.springboot.service.survey;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.survey.SurveyRepository;
import com.spring.jcompany.springboot.domain.survey.SurveyRepositorySupport;
import com.spring.jcompany.springboot.domain.survey.SurveyStatus;
import com.spring.jcompany.springboot.domain.survey.dto.SurveyResponseDto;
import com.spring.jcompany.springboot.domain.survey.dto.SurveySaveRequestDto;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyRepositorySupport surveyRepositorySupport;
    private final UserRepository userRepository;

    @Transactional
    public Long surveySaveService(SurveySaveRequestDto requestDto) {
        return surveyRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long surveyAgreeService(Long surveyId, Long userId) {
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

        return surveyRepository.save(survey.agree(userId)).getId();
    }

    @Transactional
    public Long surveyDisagreeService(Long surveyId, Long userId) {
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

        return surveyRepository.save(survey.disagree(userId)).getId();
    }

    @Transactional
    public void surveyDeleteService(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        surveyRepository.delete(survey);
    }

    @Transactional
    public void surveyStatusUpdateService(Long surveyId, SurveyStatus status) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        survey.statusUpdate(status);
    }

    @Transactional
    public SurveyResponseDto surveyResponseService(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey Not Found"));
        return new SurveyResponseDto(survey);
    }


}
