package com.shop.linepig.domain.product.api;

import com.shop.linepig.common.argumentresolver.AdminLogin;
import com.shop.linepig.domain.product.dto.request.ProductCreateRequest;
import com.shop.linepig.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductApiController {

    private final ProductService productService;
    @PostMapping("/admins/products")
    public ResponseEntity create(@RequestBody ProductCreateRequest productCreateRequest, @AdminLogin Long adminId) {
        System.out.println("productCreateRequest = " + productCreateRequest);
        productService.create(productCreateRequest,adminId);
        return null;
    }


}
