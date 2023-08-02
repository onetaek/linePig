package com.shop.linepig.common.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    private final String value;
    private final String reason;
    private final HttpStatus httpStatus;

    BaseException(final String value, final String reason, final HttpStatus httpStatus) {
        this.value = value;
        this.reason = reason;
        this.httpStatus = httpStatus;
    }

    public String getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
