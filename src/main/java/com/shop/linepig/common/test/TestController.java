package com.shop.linepig.common.test;

import com.shop.linepig.domain.member.dto.request.MemberJoinRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/test")
@Controller
public class TestController {
    @GetMapping("/joinPage")
    public String joinPage(Model model){
        model.addAttribute("member",new MemberJoinRequest());
        return "/test/joinPage";
    }

    @GetMapping("/google-login")
    public String googleLoginPage() {
        return "/test/googleLoginTest";
    }

    @GetMapping("/slider")
    public String sliderPage() {
        return "test/slider";
    }
}
