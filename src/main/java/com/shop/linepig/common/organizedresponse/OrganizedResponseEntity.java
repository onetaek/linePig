package com.shop.linepig.common.organizedresponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class OrganizedResponseEntity extends ResponseEntity {
    private static final HttpStatus httpStatus;
    private URI location;
    private BaseResponse baseResponseBody;

    public OrganizedResponseEntity(HttpStatus status) {
        super(status);
    }

//    private OrganizedResponseEntity(BaseResponse baseResponse) {
//        this.baseResponseBody = baseResponse;
//    }

//    static <T extends BaseResponse> OrganizedResponseEntity createFromSingleResponse(T response) {
//        return new OrganizedResponseEntity(response);
//    }
//
//    public OrganizedResponseEntity withLocation(@NotNull CreatedLocationFunction createdLocationFunction) {
//        this.location = (URI)createdLocationFunction.apply(this.getBaseResponseBody());
//        return OrganizedResponseEntity.createFromSingleResponse(this);
//    }

    public static HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public URI getLocation() {
        return this.location;
    }

    BaseResponse getBaseResponseBody() {
        return this.baseResponseBody;
    }

    static {
        httpStatus = HttpStatus.CREATED;
    }
}
