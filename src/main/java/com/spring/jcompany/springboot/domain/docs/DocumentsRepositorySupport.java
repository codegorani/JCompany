package com.spring.jcompany.springboot.domain.docs;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jcompany.springboot.domain.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spring.jcompany.springboot.domain.docs.QDocuments.documents;

@Repository
public class DocumentsRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public DocumentsRepositorySupport(JPAQueryFactory queryFactory) {
        super(Documents.class);
        this.queryFactory = queryFactory;
    }

    public List<Documents> findAllByUser(User user) {
        return queryFactory
                .selectFrom(documents)
                .where(documents.user.eq(user))
                .orderBy(documents.draftDate.desc())
                .fetch();
    }

    public List<Documents> findAllByApproval(User approval) {
        return queryFactory.selectFrom(documents)
                .where(documents.approval.eq(approval))
                .orderBy(documents.draftDate.desc())
                .fetch();
    }
}
