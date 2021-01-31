package com.spring.jcompany.springboot.service.card;

import com.spring.jcompany.springboot.domain.todo.board.Board;
import com.spring.jcompany.springboot.domain.todo.board.BoardRepository;
import com.spring.jcompany.springboot.domain.todo.card.Card;
import com.spring.jcompany.springboot.domain.todo.card.CardRepository;
import com.spring.jcompany.springboot.domain.todo.card.CardType;
import com.spring.jcompany.springboot.domain.todo.card.dto.CardResponseDto;
import com.spring.jcompany.springboot.domain.todo.card.dto.CardSaveRequestDto;
import com.spring.jcompany.springboot.domain.todo.card.dto.CardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CardService {

    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;

    @Transactional
    public Long cardSaveService(CardSaveRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("Board Not Exist"));
        Card card = Card.builder().title(requestDto.getTitle())
                .content(requestDto.getContent())
                .cardType(requestDto.getCardType())
                .board(board)
                .build();
        return cardRepository.save(card).getId();
    }

    @Transactional
    public void cardDeleteService(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card Not Found"));
        if (card.getCardType().equals(CardType.STANDBY)) {
            card.nextProcess(CardType.PROGRESS);
        } else if (card.getCardType().equals(CardType.PROGRESS)) {
            card.nextProcess(CardType.COMPLETE);
        } else {
            cardRepository.delete(card);
        }
    }

    @Transactional
    public CardResponseDto cardViewService(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card Not Found"));
        return new CardResponseDto(card);
    }

    @Transactional
    public Long cardUpdateService(Long id, CardUpdateRequestDto requestDto) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card Not Found"));
        card.update(requestDto.getCardType(), requestDto.getTitle(), requestDto.getContent());
        return id;
    }
}
