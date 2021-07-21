package com.spring.jcompany.springboot.domain.todo.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jcompany.springboot.domain.user.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.spring.jcompany.springboot.domain.todo.board.QBoard.board;

@Repository
public class BoardRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public BoardRepositorySupport(JPAQueryFactory queryFactory) {
        super(Board.class);
        this.queryFactory = queryFactory;
    }

    public Optional<Board> findById(Long id) {
        return queryFactory
                .selectFrom(board)
                .where(board.id.eq(id))
                .fetch().stream().findAny();
    }

    public List<Board> findAllDesc(User user) {
        return queryFactory
                .selectFrom(board)
                .where(board.user.eq(user))
                .orderBy(board.id.desc())
                .fetch();
    }

    public List<Board> findAllDescByAdmin() {
        return queryFactory
                .selectFrom(board)
                .orderBy(board.id.desc())
                .fetch();
    }
}
