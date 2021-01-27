package com.spring.jcompany.springboot.domain.todo.card;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query("SELECT c FROM Card c WHERE c.board = :boardId ORDER BY c.id DESC")
    List<Card> findAllByBoardId(Board boardId);
}
