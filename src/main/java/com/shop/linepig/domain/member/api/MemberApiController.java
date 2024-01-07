package com.shop.linepig.domain.member.api;


import com.shop.linepig.domain.member.dto.request.MemberJoinRequest;
import com.shop.linepig.domain.member.dto.request.MemberLoginRequest;
import com.shop.linepig.domain.member.dto.request.MemberUpdateRequest;
import com.shop.linepig.domain.member.dto.response.MemberBasicResponse;
import com.shop.linepig.domain.member.dto.response.MemberResponse;
import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.member.service.SnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.shop.linepig.common.constance.SessionConst.MEMBER_ID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final SnsService snsService;

    @GetMapping("/api/members/{id}")//basic 회원 데이터 조회
    public ResponseEntity findBasicById(@PathVariable Long id) {
        MemberBasicResponse memberResponse = memberService.findBasicById(id);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

    @PostMapping("/api/members/join")//회원가입
    public ResponseEntity join(@Validated @RequestBody MemberJoinRequest memberJoinRequest,
                       BindingResult bindingResult, HttpServletRequest httpServletRequest) {
        //회원가입 로직
        MemberResponse memberResponse = memberService.join(memberJoinRequest);
        if (memberResponse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(MEMBER_ID,memberResponse.getId());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PostMapping("/api/members/login")//로그인
    public ResponseEntity login(@RequestBody MemberLoginRequest memberLoginRequest, HttpServletRequest request) {
        //요청 받은 데이터로 회원을 찾기
        MemberResponse findMember = memberService.login(memberLoginRequest);
        if(findMember == null){//DB에 회원이 없을경우(아이디 or 비번이 틀렸을 경우)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//로그인 페이지로 다시 이동
        } else{//정상 로직
            HttpSession session = request.getSession();
            session.setAttribute(MEMBER_ID, findMember.getId());
            return ResponseEntity.status(HttpStatus.OK).build();//메인 페이지로 이동
        }
    }

    @PostMapping("/api/members/check-loginId")//아이디 중복체크
    public ResponseEntity checkLoginId(@RequestBody Map<String,String> map){

        String loginId = map.get("loginId");
        boolean idDuplicate = memberService.isLoginIdDuplicate(loginId);
        return idDuplicate ?
                ResponseEntity.status(HttpStatus.CONFLICT).build() :
                ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/api/admins/members/{id}/status")//회원 상태수정
    public ResponseEntity update(@PathVariable Long id,@Validated @RequestBody MemberUpdateRequest request) {
        MemberResponse memberResponse = memberService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

    @PostMapping("/api/members/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
