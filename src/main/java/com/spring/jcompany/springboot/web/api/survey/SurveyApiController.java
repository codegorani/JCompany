package com.spring.jcompany.springboot.web.api.survey;

import com.spring.jcompany.springboot.config.auth.LoginUser;
import com.spring.jcompany.springboot.domain.survey.SurveyStatus;
import com.spring.jcompany.springboot.domain.survey.dto.SurveyProgressRequestDto;
import com.spring.jcompany.springboot.domain.survey.dto.SurveySaveRequestDto;
import com.spring.jcompany.springboot.domain.user.dto.SessionUser;
import com.spring.jcompany.springboot.service.survey.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SurveyApiController {

    private final SurveyService surveyService;

    @PostMapping("/survey/progress")
    public Long surveyProgressControl(@RequestBody SurveyProgressRequestDto requestDto) {
        return surveyService.surveyProgressService(requestDto.getSurveyId(),
                requestDto.getUserId(), requestDto.getType());
    }

    @PostMapping("/survey/create")
    public Long surveySaveControl(@RequestBody SurveySaveRequestDto requestDto, @LoginUser SessionUser user) {
        return surveyService.surveySaveService(requestDto, user.getId());
    }

    @PutMapping("/survey/process/{id}")
    public String surveyProcessControl(@PathVariable("id") Long surveyId, @RequestBody String type) {
        SurveyStatus status;
        if(type.equals("PROGRESS")) {
            status = SurveyStatus.DONE;
        } else if(type.equals("WAITING")) {
            status = SurveyStatus.PROGRESS;
        } else {
            status = null;
        }
        return surveyService.surveyStatusUpdateService(surveyId, status);
    }

}
