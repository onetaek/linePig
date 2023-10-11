package com.shop.linepig.domain.board.exception;

import com.shop.linepig.common.exception.RollbackTriggeredException;

public class BoardNotFoundException extends RollbackTriggeredException {
    private static final String MESSAGE = "게시글을 찾을 수 없습니다";

    public BoardNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
