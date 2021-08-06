package com.spring.jcompany.springboot.domain.bulletin;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spring.jcompany.springboot.domain.bulletin.QBulletin.bulletin;

@Repository
public class BulletinRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;


    public BulletinRepositorySupport(JPAQueryFactory queryFactory) {
        super(Bulletin.class);
        this.queryFactory = queryFactory;
    }

    public List<Bulletin> bulletinListOrderByTitle() {
        return queryFactory.selectFrom(bulletin)
                .orderBy(bulletin.bulletinTitle.desc())
                .fetch();
    }

    public List<Bulletin> findBulletinsByCategory(BulletinCategory category) {
        return queryFactory.selectFrom(bulletin)
                .where(bulletin.bulletinCategory.eq(category))
                .orderBy(bulletin.createdDate.desc())
                .fetch();
    }

}
