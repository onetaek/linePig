package com.shop.linepig.domain.product.controller;

import com.shop.linepig.domain.product.dto.response.ProductResponse;
import com.shop.linepig.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/{id}")
    public String productPage(@PathVariable Long id, @RequestParam(required = false) Long productOptionId,  Model model) {
        ProductResponse product = productService.findById(id);
        Long findProductOptionId = productService.findProductOptionId(product.getProductOptions(), productOptionId);
        model.addAttribute("product",product);
        model.addAttribute("productOptionId",findProductOptionId);
        return "/products/product";
    }

    @GetMapping("/products")
    public String ProductsPage(Model model) {
        model.addAttribute("products",productService.findAll());
        return "/products/products";
    }

}
