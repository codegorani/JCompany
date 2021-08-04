package com.spring.jcompany.springboot.batch;

import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@SpringBootConfiguration
public class SimpleJobConfiguration {

    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public Job inactiveUserJob(JobBuilderFactory jobBuilderFactory, Step inactiveJobStep) {
        return jobBuilderFactory.get("inactiveUserJob")
//                .preventRestart()
                .start(inactiveJobStep)
                .build();
    }

    @Bean
    public Step inactiveJobStep(StepBuilderFactory stepBuilderFactory,
                                JpaPagingItemReader<User> inactiveUserReader) {
        return stepBuilderFactory.get("inactiveJobStep")
                .<User, User> chunk(10)
                .reader(inactiveUserReader)
                .processor(inactiveUserProcessor())
                .writer(inactiveUserWriter())
                .build();
    }

    @Bean(destroyMethod = "")
    @StepScope
    public JpaPagingItemReader<User> inactiveUserReader(@Value("#{jobParameters[nowDate]}") String nowDate) {
        JpaPagingItemReader<User> jpaPagingItemReader = new JpaPagingItemReader<>();
        String sql = "SELECT u FROM User u " +
                "WHERE u.lastLoginTime < :lastLoginTime " +
                "AND u.status = :status";
        Map<String, Object> map = new HashMap<>();
        LocalDateTime now = LocalDateTime.of(
                Integer.parseInt(nowDate.substring(0, 4)),
                Integer.parseInt(nowDate.substring(4, 6)),
                Integer.parseInt(nowDate.substring(6, 8)), 0, 0);
        map.put("lastLoginTime", now.minusMonths(3));
        map.put("status", UserStatus.ACTIVE);
        jpaPagingItemReader.setEntityManagerFactory(entityManagerFactory);
        jpaPagingItemReader.setPageSize(10);
        jpaPagingItemReader.setQueryString(sql);
        jpaPagingItemReader.setParameterValues(map);
        return jpaPagingItemReader;
    }

    public ItemProcessor<User, User> inactiveUserProcessor() {
        return User::inactive;
    }

    public JpaItemWriter<User> inactiveUserWriter() {
        JpaItemWriter<User> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }

}
