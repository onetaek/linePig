package com.shop.linepig.domain.member.controller;


import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.dto.MemberJoinDto;
import com.shop.linepig.domain.member.dto.MemberLoginDto;
import com.shop.linepig.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.shop.linepig.common.constance.SessionConst.MEMBER_ID;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("member",new MemberJoinDto());

        return "/members/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute(name = "member") MemberJoinDto memberJoinDto,
                       BindingResult bindingResult){

        //검증에 실패하면 다시 입력 폼으로
        if(bindingResult.hasErrors()){
            log.info("errors={}",bindingResult);
            return "/members/join";//회원가입 페이지로 돌아가기
        }

        //회원가입 로직
        Long joinedMemberId = memberService.join(memberJoinDto);

        return "/test/joinSuccess";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("member",new MemberLoginDto());
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name = "member") MemberLoginDto memberLoginDto, Model model, HttpServletRequest request){

        //요청 받은 데이터로 회원을 찾기
        Member findMember = memberService.login(memberLoginDto);

        if(findMember == null){//DB에 회원이 없을경우(아이디 or 비번이 틀렸을 경우)
            log.info("회원이 없음");
            model.addAttribute("errorMessage","loginFail");
            return "/members/login";//로그인 페이지로 다시 이동

        } else{//정상 로직
            log.info("정상 로직");
            HttpSession session = request.getSession();
            session.setAttribute(MEMBER_ID, findMember.getId());
            return "/test/loginSuccess";//메인 페이지로 이동
        }
    }

}
