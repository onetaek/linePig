package com.shop.linepig.domain.admin.controller;

import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.member.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {//AdminModelAttributeAdvice를 통해서 로그인중인 admin객체를 model에 담음(login 페이지 제외)

    private final MemberService memberService;
    private final SellerService sellerService;

    @GetMapping("/admins/login")//로그인 페이지 이동
    public String loginPage() {
        return "/admins/login";
    }

    @GetMapping(value = {"/admins/welcome", "/admins"})//관리자 메인페이지 이동
    public String welcomePage() {
        return "/admins/welcome";
    }

    @GetMapping("/admins/products")//상품 목록 페이지 (개발x)
    public String productsPage() {
        return "/admins/products/products";
    }

    @GetMapping("/admins/products/new")//상품 등록 페이지 이동
    public String productNewPage(Model model) {
        model.addAttribute("sellers", sellerService.findAll());
        return "/admins/products/productForm";
    }

    @GetMapping("/admins/members")//회원 목록 페이지 이동
    public String membersPage(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("genders", memberService.getGenders());
        model.addAttribute("statuses", memberService.getStatuses());
        return "/admins/members/members";
    }

    @GetMapping(path = "/admins/sellers/new")//판매자 등록 페이지 이동
    public String sellerNewPage(@RequestParam(required = false) Long memberId, Model model) {
        if(memberId != null)
            model.addAttribute("selectedMember",memberService.findById(memberId));
        model.addAttribute("members", memberService.findAll());
        return "/admins/sellers/sellerForm";
    }

    @GetMapping("/admins/sellers")
    public String sellersPage(Model model) {//판매자 목록 페이지 이동
        model.addAttribute("sellers", sellerService.findAll());
        return "/admins/sellers/sellers";
    }

    @GetMapping("/admins/orders")//주문목록 페이지 (개발x)
    public String ordersPage() {
        return "/admins/orders/orders";
    }

    @GetMapping("/admins/notices")//공지사항목록 페이지 (개발x)
    public String notices() {
        return "/admins/notices/notices";
    }

    @GetMapping("/admins/notices/new")//공지사항등록 페이지 (개발x)
    public String noticesNewPage() {
        return "/admins/notices/new";
    }

    @GetMapping("/admins/magazine")//메거진목록 페이지 (개발x)
    public String magazinesPage() {
        return "/admins/magazines/magazines";
    }

    @GetMapping("/admins/magazine/new")//메거진등록 페이지 (개발x)
    public String noticeNewPage() {
        return "/admins/magazines/new";
    }

    @GetMapping("/admins/reviews")//리뷰목록 페이지 (개발x)
    public String reviewsPage() {
        return "/admins/reviews/reviews";
    }
}
