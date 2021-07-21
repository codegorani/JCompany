package com.spring.jcompany.springboot.domain.todo.card;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jcompany.springboot.domain.todo.board.Board;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spring.jcompany.springboot.domain.todo.card.QCard.card;

@Repository
public class CardRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public CardRepositorySupport(JPAQueryFactory queryFactory) {
        super(Card.class);
        this.queryFactory = queryFactory;
    }

    public List<Card> findAllByBoardId(Board board) {
        return queryFactory
                .selectFrom(card)
                .where(card.board.eq(board))
                .orderBy(card.id.desc())
                .fetch();
    }
}
