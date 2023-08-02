package com.shop.linepig.common.exception;

import org.springframework.http.HttpStatus;

public class BaseRollbackException extends BaseException{
    protected BaseRollbackException(String value, String reason, HttpStatus httpStatus) {
        super(value, reason, httpStatus);
    }
}
