package com.shop.linepig.common.exception;

public class SampleCustom404Exception extends RollbackTriggeredException {

    private static final String MESSAGE = "샘플 404 예외";

    public SampleCustom404Exception() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 404;
    }
}
