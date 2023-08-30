package com.shop.linepig.domain.product.controller;

import com.shop.linepig.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{id}")
    public String productPage(@PathVariable Long id, Model model) {
        model.addAttribute("product",productService.findById(id));
        return "/products/product";
    }

    @GetMapping("/products")
    public String ProductsPage(Model model) {
        model.addAttribute("products",productService.findAll());
        return "/products/products";
    }

}
