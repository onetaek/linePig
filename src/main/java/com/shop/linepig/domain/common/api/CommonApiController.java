package com.shop.linepig.domain.common.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommonApiController {

    @PatchMapping("/api/userLanguage/{userLanguage}")
    public ResponseEntity changeUserLanguage(
            @PathVariable String userLanguage, HttpServletRequest request, HttpServletResponse response) {
        log.info("변경할 userLanguage = {}",userLanguage);
        // 기존에 userLanguage 쿠키 값을 삭제
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userLanguage".equals(cookie.getName())) {
                    // 쿠키를 삭제 (만료 시킴)
                    cookie.setMaxAge(0);
                    cookie.setPath("/"); // 루트 경로로 설정
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        // 새로운 userLanguage 값을 쿠키에 추가
        Cookie newCookie = new Cookie("userLanguage", userLanguage);
        newCookie.setPath("/"); // 루트 경로로 설정
        response.addCookie(newCookie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
