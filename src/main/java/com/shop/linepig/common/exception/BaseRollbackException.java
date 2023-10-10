package com.shop.linepig.common.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class BaseRollbackException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public BaseRollbackException(String message) {
        super(message);
    }

    public BaseRollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
