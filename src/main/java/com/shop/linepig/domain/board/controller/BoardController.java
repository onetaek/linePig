package com.shop.linepig.domain.board.controller;


import com.shop.linepig.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards/{id}")
    public String boardPage(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "boards/board";
    }

    @GetMapping("/boards")
    public String boardListPage(Model model) {
        model.addAttribute("boards",boardService.findAll());
        return "boards/boardList";
    }
}
