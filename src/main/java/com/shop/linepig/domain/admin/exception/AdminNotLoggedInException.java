package com.shop.linepig.domain.admin.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class AdminNotLoggedInException extends RollbackTriggeredException {
    private static final String MESSAGE = "관리자 계정이 로그인 되어있지 않습니다";
    public AdminNotLoggedInException() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 401;//HttpStatus.UNAUTHORIZED
    }
}
