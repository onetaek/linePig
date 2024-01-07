package com.shop.linepig.domain.cart.dto.request;

import lombok.Data;

@Data
public class CartItemCreateRequest {

    private int count;
    private Long productOptionId;

}
