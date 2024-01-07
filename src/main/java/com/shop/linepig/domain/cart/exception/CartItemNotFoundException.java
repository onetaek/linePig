package com.shop.linepig.domain.cart.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class CartItemNotFoundException extends RollbackTriggeredException {

    private final static String MESSAGE = "해당 장바구니 상품을 찾을 수 없습니다.";

    public CartItemNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
