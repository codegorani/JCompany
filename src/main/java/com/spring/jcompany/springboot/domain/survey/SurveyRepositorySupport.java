package com.spring.jcompany.springboot.domain.survey;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.spring.jcompany.springboot.domain.survey.QSurvey.survey;

@Repository
public class SurveyRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public SurveyRepositorySupport(JPAQueryFactory queryFactory) {
        super(Survey.class);
        this.queryFactory = queryFactory;
    }

    public List<Survey> findAllSurvey() {
        return queryFactory
                .selectFrom(survey)
                .orderBy(survey.createdDate.desc())
                .fetch();
    }
}
