package com.shop.linepig.domain.admin.controller;

import com.shop.linepig.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    @GetMapping("/admins/login")//로그인 페이지 이동
    public String loginPage() {
        return "/admins/login";
    }

    @GetMapping(value = {"/admins/welcome", "/admins"})//관리자 메인페이지 이동
    public String welcomePage() {
        return "/admins/welcome";
    }

    @GetMapping("/admins/products/new")//상품 등록 페이지 이동
    public String productNewPage(Model model) {
        model.addAttribute("members", memberService.findAll());
        return "/admins/products/new";
    }

    @GetMapping("/admins/members")//회원 목록 페이지 이동
    public String members(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("genders", memberService.getGenders());
        model.addAttribute("statuses", memberService.getStatuses());
        return "/admins/members/members";
    }

    @GetMapping("/admins/members/{memberId}/new-seller")//판매자 등록 페이지 이동
    public String sellerNewPage(@PathVariable Long memberId, Model model) {
        model.addAttribute("member",memberService.findById(memberId));
        return "/admins/members/new-seller";
    }
}
