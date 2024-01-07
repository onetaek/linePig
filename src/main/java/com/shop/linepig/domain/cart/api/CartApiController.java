package com.shop.linepig.domain.cart.api;

import com.shop.linepig.domain.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;

    @GetMapping("/api/admins/carts/{id}")
    public ResponseEntity findById(@PathVariable Long id) {//admi계정 유효성 검사 추가해야함
        return ResponseEntity.status(HttpStatus.OK).body(cartService.findById(id));
    }
}
