package com.spring.jcompany.springboot.batch;

import com.spring.jcompany.springboot.domain.survey.Survey;
import com.spring.jcompany.springboot.domain.survey.SurveyRepository;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SurveyTasklet implements Tasklet, StepExecutionListener {

    private final UserRepository userRepository;
    private final SurveyRepository surveyRepository;

    private List<User> userList;
    private List<Survey> surveyList;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Long> userIdList = new ArrayList<>();


        for (User user : userList) {
            userIdList.add(user.getId());
        }
        for (Survey survey : surveyList) {
            List<Long> agreeUserIdList = new ArrayList<>();
            List<Long> disagreeUserIdList = new ArrayList<>();
            List<Long> totalUserIdList = new ArrayList<>();
            if (!survey.getTotalUserId().equals("") && survey.getTotalUserId() != null) {

                totalUserIdList = Arrays.stream(survey.getTotalUserId().split(","))
                        .map(Long::parseLong).collect(Collectors.toList());
                if (!survey.getAgreeUsersId().equals("") && survey.getAgreeUsersId() != null) {
                    agreeUserIdList = Arrays.stream(survey.getAgreeUsersId().split(","))
                            .map(Long::parseLong).collect(Collectors.toList());

                    for (Long id : agreeUserIdList) {
                        if (!userIdList.contains(id)) {
                            agreeUserIdList.remove(id);
                            survey.agreeUserRemoved();
                            totalUserIdList.remove(id);
                        }
                    }
                }

                if (!survey.getDisagreeUsersId().equals("") && survey.getDisagreeUsersId() != null) {
                    disagreeUserIdList = Arrays.stream(survey.getDisagreeUsersId().split(","))
                            .map(Long::parseLong).collect(Collectors.toList());

                    for (Long id : disagreeUserIdList) {
                        if (!userIdList.contains(id)) {
                            disagreeUserIdList.remove(id);
                            survey.disagreeUserRemoved();
                            totalUserIdList.remove(id);
                        }
                    }
                }
            }

            surveyRepository.save(survey
                    .setAgreeUserId(agreeUserIdList)
                    .setDisagreeUserId(disagreeUserIdList)
                    .setTotalUserId(totalUserIdList));
        }

        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.userList = userRepository.findAll();
        this.surveyList = surveyRepository.findAll();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }


}
