package com.shop.linepig.domain.board.controller;


import com.shop.linepig.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boards/{id}")
    public String boardPage(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "boards/board";
    }

    @GetMapping(value = "/boards", params = "category")
    public String boardListPage(@RequestParam String category, Pageable pageable, Model model) {
        model.addAttribute("category",boardService.getCategory(category));
        model.addAttribute("topBoards",boardService.findAllByCategoryAndIsTop(category,Boolean.TRUE));//최상단에 위치할 게시글들
        model.addAttribute("paginationBoard",boardService.findAllByCategoryAndIsTopWithPagination(pageable,category,Boolean.FALSE));//하단에 위치할 게시글들
        return "boards/boardList";
    }
}
