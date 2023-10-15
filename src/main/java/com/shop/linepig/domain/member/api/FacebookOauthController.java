package com.shop.linepig.domain.member.api;

import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.member.entity.enumeration.MemberType;
import com.shop.linepig.domain.member.service.SnsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FacebookOauthController {

    private final SnsService snsService;

    @Value("${facebook.client.id}")
    private String facebookClientId;
    @Value("${facebook.client.secret}")
    private String facebookClientSecret;
    @Value("${facebook.redirect-uri}")
    private String facebookRedirectUri;
    @Value("${facebook.state}")
    private String state;

    @PostMapping("/oauth/facebook/login")
    public String faceBookLogin() {
        //로그인 버튼연결 주소 생성
        String facebookUrl = "https://www.facebook.com/v2.8/dialog/oauth?"+
                "client_id="+facebookClientId+
                "&redirect_uri="+facebookRedirectUri+
                "&state="+state;
        return "redirect:"+facebookUrl;
    }

    @GetMapping(value = "/oauth/facebook/callback")
    public String getFacebookSignIn(String code, String state, HttpSession session, HttpServletRequest request) throws Exception {
        String accessToken = snsService.requesFaceBooktAccesToken(session, code, facebookClientId, facebookRedirectUri, facebookClientSecret);
        Map<String, String> facebookUserInfo = snsService.getFacebookUserInfo(accessToken);

        String uniqueId = facebookUserInfo.get("id");
        String name = facebookUserInfo.get("name");

        Long memberId = snsService.snsJoin(uniqueId, name, null, MemberType.FACEBOOK);

        //로그인 처리
        HttpSession loginSession = request.getSession();
        loginSession.setAttribute(SessionConst.MEMBER_ID,memberId);
        return "redirect:/";
    }


}
