package com.shop.linepig.common.organizedresponse;

import org.springframework.stereotype.Component;

@Component
public class OrganizedResponse {

    public OrganizedResponse() {}

//    public static <C extends Collection<? extends BaseResponse>> OrganizedResponseEntity succeeded(C responses) {
//        return OrganizedResponseEntity.createFromMultiResponse(responses, HttpStatus.OK);
//    }
//
//    public static <C extends Collection<? extends BaseResponse>> OrganizedResponseEntity paged(C responses) {
//        return OrganizedResponseEntity.createFromMultiResponse(responses);
//    }
//
//    public static <T extends BaseResponse> OrganizedResponseEntity succeeded(T response) {
//        return OrganizedResponseEntity.createFromSingleResponse(response, HttpStatus.OK);
//    }
//
//    public static <T extends BaseResponse> OrganizedResponseEntity deleted() {
//        return OrganizedResponseEntity.createFromHttpStatus(HttpStatus.NO_CONTENT);
//    }
//
//    public static <E extends BaseException> OrganizedResponseEntity error(E exception) {
//        return OrganizedResponseEntity.createFromExceptionResponse(exception);
//    }
//
//    public static <T extends BaseResponse> OrganizedResponseEntity created(T response) {
//        return OrganizedResponseEntity.createFromSingleResponse(response);
//    }

}
