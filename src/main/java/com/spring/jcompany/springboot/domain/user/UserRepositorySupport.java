package com.spring.jcompany.springboot.domain.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.spring.jcompany.springboot.domain.user.QUser.user;

@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    public Optional<User> findById(Long id) {
        return queryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetch().stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        return queryFactory
                .selectFrom(user)
                .where(user.email.eq(email))
                .fetch().stream().findAny();
    }

    public Optional<User> findByName(String name) {
        return queryFactory
                .selectFrom(user)
                .where(user.name.eq(name))
                .fetch().stream().findAny();
    }

    public List<User> findAllDesc() {
        return queryFactory
                .selectFrom(user)
                .orderBy(user.userLevel.asc(), user.userTeam.desc())
                .fetch();
    }

    public List<User> findAllByUserTeam(UserTeam userTeam) {
        return queryFactory
                .selectFrom(user)
                .where(user.userTeam.eq(userTeam))
                .orderBy(user.userLevel.desc())
                .fetch();
    }

    public List<User> findAllByName(String name) {
        return queryFactory
                .selectFrom(user)
                .where(user.name.like("%" + name +"%"))
                .orderBy(user.userLevel.asc(), user.userTeam.desc())
                .fetch();
    }

    public List<User> findAllByUserLevel(UserLevel userLevel) {
        return queryFactory
                .selectFrom(user)
                .where(user.userLevel.eq(userLevel))
                .orderBy(user.userTeam.desc())
                .fetch();
    }

    public List<User> findAllByUserTeamAndRoleAndUserLevelIsGreaterThan(
            UserTeam userTeam, Role role, UserLevel userLevel) {
        return queryFactory
                .selectFrom(user)
                .where(user.userTeam.eq(userTeam), user.role.eq(role), user.userLevel.eq(userLevel))
                .orderBy(user.userLevel.desc())
                .fetch();
    }
}
