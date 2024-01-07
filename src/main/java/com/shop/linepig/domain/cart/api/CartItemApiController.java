package com.shop.linepig.domain.cart.api;

import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.cart.dto.request.CartItemUpdateRequest;
import com.shop.linepig.domain.cart.dto.request.CartItemCreateRequest;
import com.shop.linepig.domain.cart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CartItemApiController {

    private final CartItemService cartItemService;

    @PostMapping("/api/cart-items")//장바구니 담기
    public ResponseEntity create(@Login Long memberId, @RequestBody CartItemCreateRequest request) {
        cartItemService.create(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/api/cart-items/{id}")//장바구니 아이템 수량 수정
    public ResponseEntity update(@PathVariable Long id, @Login Long memberId, @RequestBody CartItemUpdateRequest request) {
        cartItemService.update(id, memberId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/api/cart-items/{id}")//장바구니 아이템 삭제
    public ResponseEntity delete(@PathVariable Long id, @Login Long memberId) {
        cartItemService.delete(id, memberId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
