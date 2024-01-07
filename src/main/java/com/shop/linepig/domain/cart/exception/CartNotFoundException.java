package com.shop.linepig.domain.cart.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class CartNotFoundException extends RollbackTriggeredException {
    private final static String MESSAGE = "장바구니를 찾을 수 없습니다.";

    public CartNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
