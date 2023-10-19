package com.shop.linepig.domain.member.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class UnauthorizedAccessException extends RollbackTriggeredException {

    private final static String MESSAGE = "적절하지 않은 접근 입니다.";

    public UnauthorizedAccessException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
