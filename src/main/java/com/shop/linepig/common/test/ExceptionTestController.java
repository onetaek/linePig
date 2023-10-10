package com.shop.linepig.common.test;


import com.shop.linepig.common.exception.SampleCustom404Exception;
import com.shop.linepig.common.exception.SampleCustom500Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ExceptionTestController {

    @GetMapping("/test/exception/404")
    public void testException1() {
        throw new SampleCustom404Exception();
    }

    @GetMapping("/api/test/exception")
    public void testApiException1() {
        throw new SampleCustom404Exception();
    }

    @GetMapping("/test/exception/500")
    public void testException2() {
        throw new SampleCustom500Exception();
    }

    @GetMapping("/api/test/exception/500")
    public void testApiException2() {
        throw new SampleCustom500Exception();
    }
}
