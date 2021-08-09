package com.spring.jcompany.springboot.batch;

import com.spring.jcompany.springboot.domain.bulletin.Bulletin;
import com.spring.jcompany.springboot.domain.bulletin.BulletinRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SimpleTasklet implements Tasklet, StepExecutionListener {

    private final UserRepository userRepository;
    private final BulletinRepository bulletinRepository;

    private List<Bulletin> bulletinList;
    private List<User> userList;

    @Transactional
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Bulletin> newData = new ArrayList<>();
        List<Long> userIds = userList.stream().map(User::getId).collect(Collectors.toList());
        for (Bulletin bulletin : bulletinList) {
            List<Long> likeUserList = Arrays.stream(bulletin.getLikeUserList().split(",")).map(Long::parseLong).collect(Collectors.toList());

            likeUserList.removeIf(id -> !userIds.contains(id));

            StringBuilder newUserList = new StringBuilder();

            for (Long id : likeUserList) {
                if (newUserList.toString().equals("")) {
                    newUserList.append(id);
                } else {
                    newUserList.append(",").append(id);
                }
            }

            newData.add(bulletin.setLikeUserList(newUserList.toString()));
        }

        bulletinRepository.saveAll(newData);
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        bulletinList = bulletinRepository.findAll();
        userList = userRepository.findAll();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
