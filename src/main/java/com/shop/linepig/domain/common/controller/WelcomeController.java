package com.shop.linepig.domain.common.controller;

import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.service.BoardService;
import com.shop.linepig.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WelcomeController {

    private final ProductService productService;
    private final BoardService boardService;

    @GetMapping("/")
    public String welcome(Model model) {
        model.addAttribute("products",productService.findAll());
        model.addAttribute("magazines",boardService.findAllByCategoryWithLimit(BoardCategory.MAGAZINE, 5));
        return "main/welcome";
    }
}
