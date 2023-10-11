package com.shop.linepig.common.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class RollbackTriggeredException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public RollbackTriggeredException(String message) {
        super(message);
    }

    public RollbackTriggeredException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }

}
