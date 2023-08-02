package com.shop.linepig.domain.member.api;


import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.dto.request.MemberSnsLoginRequest;
import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.member.service.SnsService;
import com.shop.linepig.common.constance.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static com.shop.linepig.common.constance.SnsConst.KAKAO_REDIRECT_URI;
import static com.shop.linepig.common.constance.SnsConst.KAKAO_REST_API_KEY;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Controller
public class SnsController {

    private final SnsService snsService;
    private final MemberService memberService;

    @PostMapping("/kakao/login")
    public void loginFirst(HttpServletResponse response){
        try {
            response.sendRedirect("https://kauth.kakao.com/oauth/authorize?" +
                    "client_id=" + KAKAO_REST_API_KEY +
                    "&redirect_uri="+ KAKAO_REDIRECT_URI +"&response_type=code");
        } catch (IOException e) {
            log.info("kakao 로그인 url전송중 에러");
            throw new RuntimeException(e);
        }
    }

    //카카오톡에 등록한 콜백경로
    @GetMapping("/kakao/callback")//redirect에서 설정해준 url을 여기 입력
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code,
                             @RequestParam(defaultValue = "/") String redirectURL,
                             HttpServletRequest request) throws Throwable {

        String access_Token = snsService.getKakaoAccessToken(code);//토큰을 받는다.
        HashMap<String, String> userInfo = snsService.getKakaoUserInfo(access_Token);//유저의 정보를 가져온다.

        String uniqueID = userInfo.get("uniqueID");

        log.info("카카오톡으로 가져온 회원 정보 = {}",userInfo.toString());

        MemberSnsLoginRequest memberLoginDTO = MemberSnsLoginRequest.builder()
                .loginId(uniqueID)
                .name(userInfo.get("nickName"))
                .email(userInfo.get("email"))
                .build();
        log.info("[SNSController] memberLoginDTO = {}",memberLoginDTO);

//        /** 실질적 로그인 처리 **/

        boolean isJoinedMember = memberService.isLoginIdDuplicate(uniqueID);

        Member member = null;
        if(isJoinedMember){//이메일로 중복체크 했을 때 이미 가입했다는 거니까 이메일로 회원을 찾아온다.
            member = snsService.findUser(uniqueID);
        } else{//가입하지 않은 회원 -> 가입 진행
            member = snsService.snsJoin(memberLoginDTO);
        }
        //로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID,member.getId());

        return "/test/loginSuccess";
    }
}
