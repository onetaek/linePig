package com.shop.linepig.common.exception;

public class SampleInvalidRequest extends BaseRollbackException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public SampleInvalidRequest(String message) {
        super(message);
    }

    public SampleInvalidRequest(String fieldName, String message) {
        super(MESSAGE);
        addValidation(fieldName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
