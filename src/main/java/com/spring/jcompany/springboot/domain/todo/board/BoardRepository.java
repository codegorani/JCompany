package com.spring.jcompany.springboot.domain.todo.board;

import com.spring.jcompany.springboot.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b WHERE b.user = :user ORDER BY b.id DESC")
    List<Board> findAllDesc(User user);

    @Query("SELECT b FROM Board b ORDER BY b.id DESC")
    List<Board> findAllDescByAdmin();
}
