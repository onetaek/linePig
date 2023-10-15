package com.shop.linepig.domain.member.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.linepig.domain.member.dto.request.MemberSnsLoginRequest;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import com.shop.linepig.domain.member.entity.enumeration.MemberType;
import com.shop.linepig.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class SnsService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    public String getKakaoAccessToken(String authorize_code, String kakaoResApiKey, String kakaoRedirectRri) throws Exception {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // POST 요청을 위해 기본값이 false인 setDoOutput을 true로

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");

            sb.append("&client_id="+ kakaoResApiKey); // REST_API키 본인이 발급받은 key 넣어주기
            sb.append("&redirect_uri=" + kakaoRedirectRri); // REDIRECT_URI 본인이 설정한 주소 넣어주기

            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            // 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            // jackson objectmapper 객체 생성
            ObjectMapper objectMapper = new ObjectMapper();
            // JSON String -> Map
            Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
            });

            access_Token = jsonMap.get("access_token").toString();
            refresh_Token = jsonMap.get("refresh_token").toString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, String> getKakaoUserInfo(String access_Token) throws Throwable {
        // 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, String> userInfo = new HashMap<String, String>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            try {
                // jackson objectmapper 객체 생성
                ObjectMapper objectMapper = new ObjectMapper();
                // JSON String -> Map
                Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
                });

                System.out.println(jsonMap.get("properties"));

                Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
                Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

                String nickName = properties.get("nickname").toString();
                String email = kakao_account.get("email").toString();
                String uniqueID = jsonMap.get("id").toString();

                userInfo.put("nickName", nickName);
                userInfo.put("email", email);
                userInfo.put("uniqueID", uniqueID);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public String requesFaceBooktAccesToken(HttpSession session, String code,
                                            String facebookClientId, String facebookRedirectUri,
                                            String FacebookClientSecret) throws IOException, ParseException {
        String facebookUrl = "https://graph.facebook.com/v2.8/oauth/access_token?"+
                "client_id=" + facebookClientId +
                "&redirect_uri=" + facebookRedirectUri +
                "&client_secret=" + FacebookClientSecret +
                "&code="+code;

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(facebookUrl);
        String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
        //logger.debug("facebookAccessToken / raw json : "+rawJsonString);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
        String faceBookAccessToken = (String) jsonObject.get("access_token");
        //logger.debug("facebookAccessToken / accessToken : "+faceBookAccessToken);

        session.setAttribute("faceBookAccessToken", faceBookAccessToken);
        return faceBookAccessToken;
    }

    public Map<String, String> getFacebookUserInfo(String accessToken) throws Exception {
        String facebookUrl = "https://graph.facebook.com/me?"+
                "access_token="+accessToken+
                "&fields=id,name,email,picture";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(facebookUrl);
        String rawJsonString = client.execute(getRequest, new BasicResponseHandler());
        //logger.debug("facebookAccessToken / rawJson!  : "+rawJsonString);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(rawJsonString);
        //logger.debug("facebookUserDataLoadAndSave / raw json : "+jsonObject);

        String id = (String) jsonObject.get("id");
        String name = (String) jsonObject.get("name");

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("id",id);
        userInfo.put("name",name);
        /* 가져온 데이터를 서비스에 맞춰 가공하는 로직*/
        return userInfo;
    }

    public Long snsJoin(String uniqueId,String nickName,String email, MemberType type) {
        Member findMember = memberRepository.findByLoginId(uniqueId);
        if (findMember != null) { //이미 회원 가입한 유저라면
            return findMember.getId();
        } else {
            MemberSnsLoginRequest memberLoginRequest = MemberSnsLoginRequest.builder()
                    .loginId(uniqueId)
                    .name(nickName)
                    .email(email)
                    .joinOn(LocalDateTime.now())
                    .status(MemberStatus.NORMAL)
                    .type(type)
                    .build();
            Member savedMember = memberRepository.save(MemberSnsLoginRequest.toEntity(memberLoginRequest));
            return savedMember.getId();
        }
    }
}
