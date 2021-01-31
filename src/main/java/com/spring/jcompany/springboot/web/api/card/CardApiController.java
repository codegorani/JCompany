package com.spring.jcompany.springboot.web.api.card;

import com.spring.jcompany.springboot.domain.todo.card.dto.CardSaveRequestDto;
import com.spring.jcompany.springboot.domain.todo.card.dto.CardUpdateRequestDto;
import com.spring.jcompany.springboot.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CardApiController {

    private final CardService cardService;

    @PostMapping("/api/v1/card")
    public Long cardSaveRequestControl(@RequestBody CardSaveRequestDto requestDto) {
        return cardService.cardSaveService(requestDto);
    }

    @DeleteMapping("/api/v1/card/{id}")
    public void cardDeleteRequestControl(@PathVariable("id") Long id) {
        cardService.cardDeleteService(id);
    }

    @PutMapping("/api/v1/card/{id}")
    public Long cardUpdateRequestControl(@PathVariable("id") Long id, @RequestBody CardUpdateRequestDto requestDto) {
        return cardService.cardUpdateService(id, requestDto);
    }
}
