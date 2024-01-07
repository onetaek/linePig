package com.shop.linepig.domain.admin.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class AdminNotFoundException extends RollbackTriggeredException {
    private static final String MESSAGE = "관리자를 찾을 수 없습니다";

    public AdminNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
