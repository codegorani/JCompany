package com.spring.jcompany.springboot.web.controller;

import com.spring.jcompany.springboot.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/menu/todo")
    public String todoListPage(Model model) {
        model.addAttribute("boards", boardService.boardListViewService());
        return "menu/todo/todo";
    }

    @GetMapping("/menu/todo-save")
    public String todoSavePage() {
        return "menu/todo/todo-save";
    }

    @GetMapping("/menu/todo/{id}")
    public String viewBoardPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.boardViewService(id));
        return "menu/todo/board-view";
    }

    @GetMapping("/menu/card-save/{id}")
    public String cardSavePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("boardId", id);
        return "menu/todo/card-save";
    }
}
