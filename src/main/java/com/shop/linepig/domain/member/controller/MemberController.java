package com.shop.linepig.domain.member.controller;


import com.shop.linepig.domain.member.dto.request.MemberJoinRequest;
import com.shop.linepig.domain.member.dto.request.MemberLoginRequest;
import com.shop.linepig.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")//회원가입 페이지 이동
    public String join(Model model) {
        model.addAttribute("member",new MemberJoinRequest());
        return "/members/join";
    }

    @GetMapping("/login")//로그인 페이지 이동
    public String loginPage(Model model) {
        model.addAttribute("member",new MemberLoginRequest());
        return "members/login";
    }


}
