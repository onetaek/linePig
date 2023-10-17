package com.shop.linepig.domain.cart.api;

import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.cart.dto.request.CartItemCreateRequest;
import com.shop.linepig.domain.cart.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class CartItemApiController {

    private final CartItemService cartItemService;

    @PostMapping("/cart-items")
    public ResponseEntity create(@Login Long memberId, @RequestBody CartItemCreateRequest request) {
        cartItemService.create(request, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
