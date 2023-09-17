package com.shop.linepig.common.exception;

public class SampleCustomException extends BaseRollbackException{

    private static final String MESSAGE = "";

    public SampleCustomException() {
        super(MESSAGE);
    }
    @Override
    public int getStatusCode() {
        return 404;
    }
}
