package com.shop.linepig.domain.product.api;

import com.shop.linepig.common.argumentresolver.AdminLogin;
import com.shop.linepig.domain.product.dto.request.ProductCreateRequest;
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

    @PostMapping("/admins/products")
    public ResponseEntity create(@RequestBody ProductCreateRequest productCreateRequest, @AdminLogin Long id) {
        System.out.println("productCreateRequest = " + productCreateRequest);


        return null;
    }


}
