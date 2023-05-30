package com.shop.linepig.controller.member;


import com.shop.linepig.domain.member.Member;
import com.shop.linepig.dto.member.MemberJoinDto;
import com.shop.linepig.dto.member.MemberLoginDto;
import com.shop.linepig.service.member.MemberService;
import com.shop.linepig.util.constValue.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.shop.linepig.util.constValue.SessionConst.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@Controller
public class MemberController {

    private final MemberService memberService;

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

    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginDto memberLoginDto, Model model, HttpServletRequest request){

        //요청 받은 데이터로 회원을 찾기
        Member findMember = memberService.login(memberLoginDto);

        if(findMember == null){//DB에 회원이 없을경우(아이디 or 비번이 틀렸을 경우)
            model.addAttribute("error","");
            return null;//로그인 페이지로 다시 이동

        } else{//정상 로직
            HttpSession session = request.getSession();
            session.setAttribute(MEMBER_ID, findMember.getId());
            return null;//메인 페이지로 이동
        }
    }
}
