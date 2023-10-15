package com.shop.linepig.domain.member.api;

import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.member.entity.enumeration.MemberType;
import com.shop.linepig.domain.member.service.SnsService;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class GoogleOauthController {

    private final SnsService snsService;

    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;
    @Value("${google.redirect-uri}")
    private String googleRedirectUri;

    @PostMapping("/oauth/google/login")
    public String loginUrlGoogle(){
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=" + googleRedirectUri +"&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return "redirect:"+reqUrl;
    }

    @RequestMapping(value="/oauth/google/callback", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode, HttpServletRequest request){
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri(googleRedirectUri)
                .grantType("authorization_code").build();
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken=resultEntity.getBody().getId_token();
        Map<String, String> map=new HashMap<>();
         map.put("id_token",jwtToken);
        ResponseEntity<GoogleInfResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInfResponse.class);

        String kid = resultEntity2.getBody().getKid();
        String name = resultEntity2.getBody().getName();
        String email=resultEntity2.getBody().getEmail();

        Long memberId = snsService.snsJoin(kid, name, email, MemberType.GOOGLE);
        //로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID,memberId);
        return "redirect:/";
    }


    @Data
    @Builder
    public static class GoogleRequest {
        private String clientId;    // 애플리케이션의 클라이언트 ID
        private String redirectUri; // Google 로그인 후 redirect 위치
        private String clientSecret;    // 클라이언트 보안 비밀
        private String responseType;    // Google OAuth 2.0 엔드포인트가 인증 코드를 반환하는지 여부
        private String scope;   // OAuth 동의범위
        private String code;
        private String accessType;  // 사용자가 브라우저에 없을 때 애플리케이션이 액세스 토큰을 새로 고칠 수 있는지 여부
        private String grantType;
        private String state;
        private String includeGrantedScopes;    // 애플리케이션이 컨텍스트에서 추가 범위에 대한 액세스를 요청하기 위해 추가 권한 부여를 사용
        private String loginHint;   // 애플리케이션이 인증하려는 사용자를 알고 있는 경우 이 매개변수를 사용하여 Google 인증 서버에 힌트를 제공
        private String prompt;  // default: 처음으로 액세스를 요청할 때만 사용자에게 메시지가 표시
    }
    @Data
    @NoArgsConstructor
    public static class GoogleResponse {
        private String access_token; // 애플리케이션이 Google API 요청을 승인하기 위해 보내는 토큰
        private String expires_in;   // Access Token의 남은 수명
        private String refresh_token;    // 새 액세스 토큰을 얻는 데 사용할 수 있는 토큰
        private String scope;
        private String token_type;   // 반환된 토큰 유형(Bearer 고정)
        private String id_token;
    }

    @Data
    @NoArgsConstructor
    public static class GoogleInfResponse {
        private String iss;
        private String azp;
        private String aud;
        private String sub;
        private String email;
        private String email_verified;
        private String at_hash;
        private String name;
        private String picture;
        private String given_name;
        private String family_name;
        private String locale;
        private String iat;
        private String exp;
        private String alg;
        private String kid;
        private String typ;
    }
}
