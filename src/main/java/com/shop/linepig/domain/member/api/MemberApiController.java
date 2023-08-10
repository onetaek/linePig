package com.shop.linepig.domain.member.api;


import com.shop.linepig.domain.member.dto.request.MemberUpdateRequest;
import com.shop.linepig.domain.member.dto.response.MemberResponse;
import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.member.service.SnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberApiController {

    private final MemberService memberService;

    private final SnsService snsService;

    @PostMapping("/api/members/check-loginId")
    public ResponseEntity checkLoginId(@RequestBody Map<String,String> map){

        String loginId = map.get("loginId");
        log.info("loginId = {}",loginId);
        boolean idDuplicate = memberService.isLoginIdDuplicate(loginId);
        log.info("isDuplicate = {}",idDuplicate);

        return idDuplicate ?
                ResponseEntity.status(HttpStatus.CONFLICT).build() :
                ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/api/admins/members/{id}")
    public ResponseEntity update(@PathVariable Long id,@Validated @RequestBody MemberUpdateRequest request) {
        MemberResponse memberResponse = memberService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }



    //카카오톡에 등록한 콜백경로
    @GetMapping("/api/members/kakao/callback")//redirect에서 설정해준 url을 여기 입력
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code,
                             @RequestParam(defaultValue = "/") String redirectURL,
                             HttpServletRequest request) throws Throwable {

        String access_Token = snsService.getKakaoAccessToken(code);//토큰을 받는다.
        HashMap<String, String> userInfo = snsService.getKakaoUserInfo(access_Token);//유저의 정보를 가져온다.

//        String email = snsService.changeKakaoIdToEmail(userInfo.get("uniqueID").toString());//카카오에서 받은 id를 email형식으로 변환한다.
//        MemberSNSLoginDTO memberLoginDTO = MemberSNSLoginDTO
//                .builder()
//                .email(email)
//                .profileImg(userInfo.get("profileImg"))
//                .name(userInfo.get("nickName"))
//                .build();//로그인할때 필요한 정보만들어있는 MemberSNSLoginDTO객체로 만들어준다.
//        log.info("[SNSController] memberLoginDTO = {}",memberLoginDTO);
//
//        /** 실질적 로그인 처리 **/
//        boolean IsLoginMember = memberService.emailDuplCheck(email);// 중복 이메일 체크
//        Member member = null;
//        if(IsLoginMember){//이메일로 중복체크 했을 때 이미 가입했다는 거니까 이메일로 회원을 찾아온다.
//            member = snsService.findUser(email);
//        } else{//가입하지 않은 회원 -> 가입 진행
//            member = snsService.snsJoin(memberLoginDTO);
//        }
//        //로그인 처리
//        HttpSession session = request.getSession();
//        session.setAttribute(SessionConst.MEMBER_ID,member.getId());

        return "redirect:" + redirectURL;
    }

}
