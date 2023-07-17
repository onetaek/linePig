package com.shop.linepig.domain.magazine.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorTestController {

    @GetMapping("/test/editor")
    public String editor(){
        return "/test/editor";
    }
}
