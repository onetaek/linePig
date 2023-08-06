package com.shop.linepig.common.refactoredresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RefactoredResponseEntity extends ResponseEntity {

    public RefactoredResponseEntity(HttpStatus status) {
        super(status);
    }


}
