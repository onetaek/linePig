package com.shop.linepig.domain.product.api;

import com.shop.linepig.common.argumentresolver.AdminLogin;
import com.shop.linepig.domain.product.dto.request.ProductCreateRequest;
import com.shop.linepig.domain.product.dto.response.ProductBasicResponse;
import com.shop.linepig.domain.product.dto.response.ProductResponse;
import com.shop.linepig.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/products/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        ProductResponse productResponse = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    @GetMapping("/products")
    public ResponseEntity findAll() {
        List<ProductBasicResponse> productBasicResponses = productService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(productBasicResponses);
    }
}
