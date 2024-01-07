package com.shop.linepig.domain.cart.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartItemUpdateRequest {

    @NotNull
    private int count;

}
