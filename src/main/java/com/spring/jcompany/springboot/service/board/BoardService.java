package com.spring.jcompany.springboot.service.board;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import com.spring.jcompany.springboot.domain.todo.board.BoardRepository;
import com.spring.jcompany.springboot.domain.todo.board.dto.BoardListResponseDto;
import com.spring.jcompany.springboot.domain.todo.board.dto.BoardResponseDto;
import com.spring.jcompany.springboot.domain.todo.board.dto.BoardSaveRequestDto;
import com.spring.jcompany.springboot.domain.todo.board.dto.BoardUpdateRequestDto;
import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.todo.card.CardRepository;
import com.spring.jcompany.springboot.domain.todo.card.dto.CardBoardResponseDto;
import com.spring.jcompany.springboot.domain.user.User;
import com.spring.jcompany.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final CardRepository cardRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * Database에 board를 저장
     * @param requestDto 요청 파라미터를 담고 있는 Dto
     * @return 저장된 board의 데이터베이스상 id
     */
    @Transactional
    public Long boardSaveService(BoardSaveRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        Board board = Board.builder()
                .title(requestDto.getTitle())
                .author(requestDto.getAuthor())
                .user(user)
                .build();
        return boardRepository.save(board).getId();
    }


    @Transactional
    public BoardResponseDto boardViewService(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board Not Exist"));
        List<CardBoardResponseDto> cardList = cardRepository.findAllByBoardId(board)
                .stream().map(CardBoardResponseDto::new).collect(Collectors.toList());
        return BoardResponseDto.builder().boardEntity(board).cardList(cardList).build();
    }


    @Transactional
    public List<BoardListResponseDto> boardListViewService(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        return boardRepository.findAllDesc(user).stream()
                .map(BoardListResponseDto::new).collect(Collectors.toList());
    }


    @Transactional
    public void boardDeleteService(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board Not Exist"));
        boardRepository.delete(board);
    }

    @Transactional
    public Long boardUpdateService(Long id, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board Not Exist"));
        board.update(requestDto.getTitle());
        return id;
    }
}
