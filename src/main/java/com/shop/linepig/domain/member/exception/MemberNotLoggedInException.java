package com.shop.linepig.domain.member.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class MemberNotLoggedInException extends RollbackTriggeredException {
    private static final String MESSAGE = "회원 계정이 로그인 되어있지 않습니다";
    public MemberNotLoggedInException() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 401;//HttpStatus.UNAUTHORIZED
    }
}
