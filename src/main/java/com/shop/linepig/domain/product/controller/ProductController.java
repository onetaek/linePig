package com.shop.linepig.domain.product.controller;

import com.shop.linepig.domain.product.dto.request.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    @GetMapping("/admin/product/new")
    public String create(@ModelAttribute ProductCreateRequest productCreateRequest) {



        return null;
    }

}
