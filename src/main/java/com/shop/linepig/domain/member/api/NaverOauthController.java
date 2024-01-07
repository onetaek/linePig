package com.shop.linepig.domain.member.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.member.entity.enumeration.MemberType;
import com.shop.linepig.domain.member.service.SnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NaverOauthController {

    private final SnsService snsService;

    @Value("${naver.cliend.id}")
    private String naverClientId;
    @Value("${naver.client.secret}")
    private String naverClientSecret;
    @Value("${naver.redirect-uri}")
    private String naverRedirectUri;


    @PostMapping("/oauth/naver/login")
    public void naverLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect(createApiURL(request, response, naverClientId));//apiURL를 생성해서 redirect
        } catch (IOException e) {
            log.info("naver login sendRedirect 요류");
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/oauth/naver/callback")//CallBack URL
    public String naverLoginGETToken(HttpServletRequest request,
                                     @RequestParam String code,
                                     @RequestParam String state,
                                     HttpServletResponse response) {
        String naverAcessToken = createNaverAcessToken(response, naverClientId, naverClientSecret, code, state);

        HashMap<String, String> userInfo = getUserInfo(naverAcessToken);

        String uniqueId = userInfo.get("id");
        String name = userInfo.get("name");
        String email = userInfo.get("email");

        /** 실질적 로그인 처리 **/
        Long memberId = snsService.snsJoin(uniqueId, name, email, MemberType.NAVER);

        //로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.MEMBER_ID,memberId);
        return "redirect:/";
    }



    private String createApiURL(HttpServletRequest request, HttpServletResponse response, String clientId) {
        String redirectURI;
        try {
            redirectURI = URLEncoder.encode(naverRedirectUri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("naver login encoding error");
            throw new RuntimeException(e);
        }
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
        apiURL += "&client_id=" + clientId;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&state=" + state;
        request.getSession().setAttribute("state", state);
        return apiURL;
    }

    private HashMap<String, String> getUserInfo(String token) {
        HashMap<String, String> userInfo = null;
        String header = "Bearer " + token; // Bearer 다음에 공백 추가
        String apiURL = "https://openapi.naver.com/v1/nid/me";


        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(apiURL,requestHeaders);
        log.info("responseBody = {}",responseBody);


        // jackson objectmapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();
        // JSON String -> Map
        try {
            Map<String, Object> jsonMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
            log.info("jsonMap = {}",jsonMap);
            userInfo = (HashMap<String, String>) jsonMap.get("response");

            log.info("[네이버 응답] id = {}",userInfo.get("id"));
            log.info("[네이버 응답] profile_image = {},",userInfo.get("profile_image"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userInfo;
    }


    private String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private String createNaverAcessToken(HttpServletResponse response, String clientId, String clientSecret, String code, String state) {
        String redirectURI;
        try {
            redirectURI = URLEncoder.encode(naverRedirectUri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info("naver login redirectURI encoding error");
            throw new RuntimeException(e);
        }
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        System.out.println("apiURL="+apiURL);

        String access_Token;
        String refresh_Token;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            System.out.print("responseCode="+responseCode);
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer res = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if(responseCode==200) {
                log.info("naver에서 제공 토큰 정보 = {}",res.toString());
                // jackson objectmapper 객체 생성
                ObjectMapper objectMapper = new ObjectMapper();
                // JSON String -> Map
                Map<String, Object> jsonMap = objectMapper.readValue(res.toString(), new TypeReference<Map<String, Object>>() {
                });

                access_Token = jsonMap.get("access_token").toString();
                refresh_Token = jsonMap.get("refresh_token").toString();

                log.info("[naver] access_token : " + access_Token);
                log.info("[naver] refresh_token : " + refresh_Token);

                return access_Token;
            }
        } catch (Exception e) {
            log.info("naver token접근 오류");
        }
        return null;
    }

    private HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }
            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
