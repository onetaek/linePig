package com.shop.linepig.domain.cart.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private Long id;
    List<CartItemResponse> cartItemResponses;

}
