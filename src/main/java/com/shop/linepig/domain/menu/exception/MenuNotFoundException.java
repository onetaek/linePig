package com.shop.linepig.domain.menu.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class MenuNotFoundException extends RollbackTriggeredException {

    private static final String MESSAGE = "메뉴를 찾을 수 없습니다.";

    public MenuNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
