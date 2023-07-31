package com.shop.linepig.common.responseentity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class OrganizedResponseEntity extends ResponseEntity {
    public OrganizedResponseEntity(HttpStatus status) {
        super(status);
    }

    public OrganizedResponseEntity(Object body, HttpStatus status) {
        super(body, status);
    }

    public OrganizedResponseEntity(MultiValueMap headers, HttpStatus status) {
        super(headers, status);
    }

    public OrganizedResponseEntity(Object body, MultiValueMap headers, HttpStatus status) {
        super(body, headers, status);
    }
}
