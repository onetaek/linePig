package com.shop.linepig.controller.test;

import com.shop.linepig.dto.member.MemberJoinDto;
import com.shop.linepig.dto.member.MemberLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/test")
@Controller
public class TestController {


    @GetMapping("/mainPage")
    public String mainPage(){
        return "/test/mainPage";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model){

        model.addAttribute("member",new MemberLoginDto());

        return "/test/loginPage";
    }

    @GetMapping("/joinPage")
    public String joinPage(Model model){

        model.addAttribute("member",new MemberJoinDto());

        return "/test/joinPage";
    }
}
