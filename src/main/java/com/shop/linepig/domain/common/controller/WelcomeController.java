package com.shop.linepig.domain.common.controller;

import com.shop.linepig.common.argumentresolver.Login;
import com.shop.linepig.domain.member.dto.response.MemberResponse;
import com.shop.linepig.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class WelcomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String welcome(@Login Long memberId, Model model) {
        if (memberId != null) {
            MemberResponse findMember = memberService.findById(memberId);
            model.addAttribute("member",findMember);
        }
        return "/main/welcome";
    }
}
