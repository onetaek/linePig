package com.shop.linepig.common.exception;

public class SampleCustom500Exception extends BaseRollbackException{

    private static final String MESSAGE = "샘플 500 예외";

    public SampleCustom500Exception() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 500;
    }
}
