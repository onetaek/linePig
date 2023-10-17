package com.shop.linepig.domain.product.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class ProductOptionNotFoundException extends RollbackTriggeredException {

    private static final String MESSAGE = "제품을 찾을 수 없습니다";

    public ProductOptionNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
