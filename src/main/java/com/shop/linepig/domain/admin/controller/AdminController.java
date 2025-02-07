package com.shop.linepig.domain.admin.controller;

import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import com.shop.linepig.domain.board.service.BoardService;
import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.member.service.SellerService;
import com.shop.linepig.domain.product.service.ProductService;
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
    private final ProductService productService;
    private final BoardService boardService;

    @GetMapping("/admins/login")//로그인 페이지 이동
    public String loginPage() {
        return "admins/login";
    }

    @GetMapping(value = {"/admins/welcome", "/admins"})//관리자 메인페이지 이동
    public String welcomePage() {
        return "admins/welcome";
    }

    @GetMapping("/admins/products")//상품 목록 페이지 (개발x)
    public String productsPage(Model model) {
        model.addAttribute("products",productService.findAll());
        return "admins/products/products";
    }

    @GetMapping("/admins/products/new")//상품 등록 페이지 이동
    public String productNewPage(Model model) {
        model.addAttribute("sellers", sellerService.findAll());
        model.addAttribute("unitOfCurrencies",productService.getUnitOfCurrencies());
        return "admins/products/productForm";
    }

    @GetMapping("/admins/members")//회원 목록 페이지 이동
    public String membersPage(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("genders", memberService.getGenders());
        model.addAttribute("statuses", memberService.getStatuses());
        return "admins/members/members";
    }

    @GetMapping(path = "/admins/sellers/new")//판매자 등록 페이지 이동
    public String sellerNewPage(@RequestParam(required = false) Long memberId, Model model) {
        if(memberId != null)
            model.addAttribute("selectedMember",memberService.findById(memberId));
        model.addAttribute("members", memberService.findAll());
        return "admins/sellers/sellerForm";
    }

    @GetMapping("/admins/sellers")
    public String sellersPage(Model model) {//판매자 목록 페이지 이동
        model.addAttribute("sellers", sellerService.findAll());
        return "admins/sellers/sellers";
    }

    @GetMapping("/admins/boards")//게시판목록 페이지
    public String notices(@RequestParam(required = false) String category,@RequestParam(required = false) Boolean isTop ,Model model) {
        model.addAttribute("boards",boardService.findAllByCategoryAndIsTop(category, isTop));
        model.addAttribute("categories",boardService.getCategories());
        model.addAttribute("statuses",boardService.getStatuses());
        return "admins/boards/boardList";
    }

    @GetMapping("/admins/boards/new")//게시판등록 페이지
    public String noticesNewPage(Model model) {
        model.addAttribute("categories",boardService.getCategories());
        model.addAttribute("statuses",boardService.getStatuses());
        return "admins/boards/boardForm";
    }

    @GetMapping(value = "/admins/boards", params = "category")//메거진목록 페이지 (개발x)
    public String magazinesPage(Model model, @RequestParam String category) {
        model.addAttribute("magazines",boardService.findAllByCategory(BoardCategory.fromCode(category)));
        return "admins/boards/magazines";
    }

    @GetMapping(value = "/admins/boards/new", params = "category")//메거진등록 페이지 (개발x)
    public String noticeNewPage(Model model) {
        model.addAttribute("statuses",boardService.getStatuses());
        return "admins/boards/magazineForm";
    }

    //---미개발

    @GetMapping("/admins/orders")//주문목록 페이지 (개발x)
    public String ordersPage() {
        return "admins/orders/orders";
    }





    @GetMapping("/admins/reviews")//리뷰목록 페이지 (개발x)
    public String reviewsPage() {
        return "admins/reviews/reviews";
    }
}
