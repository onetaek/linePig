package com.shop.linepig.domain.cart.controller;

import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.cart.service.CartService;
import com.shop.linepig.domain.member.exception.UnauthorizedAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/members/{memberId}/carts")
    public String findByMemberId(@PathVariable Long memberId, @Login Long loginMemberId, Model model) {
        if (memberId.equals(loginMemberId)) throw new UnauthorizedAccessException();
        model.addAttribute("cart", cartService.findByMemberId(memberId));
        return "carts/cartList";
    }
}
