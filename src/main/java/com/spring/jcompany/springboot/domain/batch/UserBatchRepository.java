package com.spring.jcompany.springboot.domain.batch;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jcompany.springboot.domain.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.spring.jcompany.springboot.domain.user.QUser.user;

@Repository
public class UserBatchRepository extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public UserBatchRepository(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    public List<User> findAllHaveToBeDormantUser(LocalDateTime now) {
        return queryFactory
                .selectFrom(user)
                .where(user.lastLoginTime.before(now.minusMonths(3)))
                .fetch();
    }
}
