package com.shop.linepig.domain.member.api;


<<<<<<<< HEAD:src/main/java/com/shop/linepig/domain/member/api/KakaoOauthController.java
import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.member.dto.request.MemberSnsLoginRequest;
import com.shop.linepig.domain.member.entity.enumeration.MemberType;
import com.shop.linepig.domain.member.service.SnsService;
========
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.dto.request.MemberSnsLoginRequest;
import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.member.service.SnsService;
import com.shop.linepig.common.constance.SessionConst;
>>>>>>>> origin/master:src/main/java/com/shop/linepig/domain/member/api/SnsController.java
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Controller
public class KakaoOauthController {

    private final SnsService snsService;

    @Value("${kakao.rest-api-key}")
    private String kakaoRestApiKey;
    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @PostMapping("/kakao/login")
    public void loginFirst(HttpServletResponse response){
        try {
            response.sendRedirect("https://kauth.kakao.com/oauth/authorize?" +
                    "client_id=" + kakaoRestApiKey +
                    "&redirect_uri="+ kakaoRedirectUri +"&response_type=code");
        } catch (IOException e) {
            log.info("kakao 로그인 url전송중 에러");
            throw new RuntimeException(e);
        }
    }

    //카카오톡에 등록한 콜백경로
    @GetMapping("/kakao/callback")//redirect에서 설정해준 url을 여기 입력
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code,
                             HttpServletRequest request) throws Throwable {

        String access_Token = snsService.getKakaoAccessToken(code, kakaoRestApiKey, kakaoRedirectUri);//토큰을 받는다.
        HashMap<String, String> userInfo = snsService.getKakaoUserInfo(access_Token);//유저의 정보를 가져온다.

        String uniqueID = userInfo.get("uniqueID");
        String nickName = userInfo.get("nickName");
        String email = userInfo.get("email");

        log.info("카카오톡으로 가져온 회원 정보 = {}",userInfo);

        MemberSnsLoginRequest memberLoginDTO = MemberSnsLoginRequest.builder()
                .loginId(uniqueID)
                .name(userInfo.get("nickName"))
                .email(userInfo.get("email"))
                .build();
        log.info("[SNSController] memberLoginDTO = {}",memberLoginDTO);

        /** 실질적 로그인 처리 **/
        Long memberId = snsService.snsJoin(uniqueID, nickName, email, MemberType.KAKAO);

        //로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID,memberId);
        return "redirect:/";
    }
}
