package com.shop.linepig.common.advice;

import com.shop.linepig.common.exception.BaseRollbackException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvisor {

    static class UnhandledException extends BaseRollbackException {
        private UnhandledException(String value, String reason, HttpStatus httpStatus) {
            super(value, reason, httpStatus);
        }

        public static UnhandledException of(Throwable t) {
            return new UnhandledException(
                    "-1",
                    String.format("%s: %s", t.getClass().toString(), t.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @ExceptionHandler(Throwable.class)
//    public StructuredResponseEntity exception(final Throwable t) {
//        if (t instanceof BaseException) {
//            return StructuredResponse.error((BaseException) t);
//        } else if (t.getCause() != null && t.getCause() instanceof BaseException) {
//            return StructuredResponse.error((BaseException) t.getCause());
//        } else {
//            return StructuredResponse.error(UnhandledException.of(t));
//        }
//    }

}
