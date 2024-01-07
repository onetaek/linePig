package com.shop.linepig.domain.member.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class MemberNotFoundException extends RollbackTriggeredException {
    private static final String MESSAGE = "회원을 찾을 수 없습니다";

    public MemberNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
