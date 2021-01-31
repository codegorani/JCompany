package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class CardController {

    private final CardService cardService;

    @GetMapping("/menu/card/{id}")
    public String cardViewRequestControl(@PathVariable("id") Long id, Model model) {
        model.addAttribute("card", cardService.cardViewService(id));
        return "menu/todo/card-view";
    }
}
