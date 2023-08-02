package com.shop.linepig.common.test;

import com.shop.linepig.domain.member.dto.request.MemberJoinRequest;
import com.shop.linepig.domain.member.dto.request.MemberLoginRequest;
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

        model.addAttribute("member",new MemberLoginRequest());

        return "/test/loginPage";
    }

    @GetMapping("/joinPage")
    public String joinPage(Model model){

        model.addAttribute("member",new MemberJoinRequest());

        return "/test/joinPage";
    }

    @GetMapping("/adminPage")
    public String adminPage() {
        return "/admin/form";
    }

    @GetMapping("/admin404Page")
    public String admin404Page() {
        return "/admin/404";
    }
}
