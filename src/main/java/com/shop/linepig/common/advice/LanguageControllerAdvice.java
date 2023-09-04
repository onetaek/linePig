package com.shop.linepig.common.advice;

import com.shop.linepig.common.constance.UserLanguageConst;
import com.shop.linepig.domain.common.controller.WelcomeController;
import com.shop.linepig.domain.product.controller.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice(basePackageClasses = { WelcomeController.class, ProductController.class })
public class LanguageControllerAdvice {

    @ModelAttribute
    public void selectLanguage(HttpServletRequest request, HttpServletResponse response,
                               @CookieValue(name = "userLanguage", required = false) String userLanguage, Model model) {
        log.info("쿠키 설정 advice 시작, userLanguage = {}",userLanguage);
        // 쿠키에 언어 설정이 없으면 기본 언어를 설정하거나 Accept-Language 헤더를 기반으로 설정
        if (userLanguage == null) {
            String acceptLanguageHeader = request.getHeader("Accept-Language");

            String preferredLanguage = this.getPreferredLanguage(acceptLanguageHeader);// 우순 순위가 가장 높은 언어 추출

            log.info("preferredLanguage = {}",preferredLanguage);

            if (preferredLanguage != null) {
                // 우선 순위가 가장 높은 언어가 한국어인 경우 -> 가장 높은 우선 순위 언어를 기준으로 쿠키 설정
                if ("ko".equalsIgnoreCase(preferredLanguage)||"ko-KR".equalsIgnoreCase(preferredLanguage)) {
                    // 한국어로 쿠키 설정
                    log.info("userLanguage 쿠기에 ko를 저장");
                    Cookie languageCookie = new Cookie("userLanguage", UserLanguageConst.KO);
                    languageCookie.setMaxAge(365 * 24 * 60 * 60); // 쿠키 유효 기간 설정 (예: 1년)
                    response.addCookie(languageCookie);
                    model.addAttribute("userLanguage","ko");
                } else {// 우선 순위가 가장 높은 언어가 한국어가 아닌 경우 -> 영어로 쿠키 설정
                    log.info("userLanguage 쿠기에 en를 저장");
                    Cookie languageCookie = new Cookie("userLanguage", UserLanguageConst.EN);
                    languageCookie.setMaxAge(365 * 24 * 60 * 60); // 쿠키 유효 기간 설정 (예: 1년)
                    response.addCookie(languageCookie);
                    model.addAttribute("userLanguage","en");
                }
            }
        } else {
            model.addAttribute("userLanguage",userLanguage);
        }
    }

    private String getPreferredLanguage(String acceptLanguageHeader) {
        // 헤더를 파싱하여 언어와 우선 순위를 저장할 변수 초기화
        String preferredLanguage = null;
        double highestPriority = 0.0;

        // Accept-Language 헤더 값들을 쉼표로 분리
        String[] languages = acceptLanguageHeader.split(",");

        for (String language : languages) {
            String[] parts = language.trim().split(";q=");
            String languageCode = parts[0].trim();
            double priority = 1.0; // 기본 우선 순위 설정 (q=가 없는 경우)

            // 우선 순위(q)가 있는 경우 우선 순위 값을 파싱
            if (parts.length > 1) {
                try {
                    priority = Double.parseDouble(parts[1].trim());
                } catch (NumberFormatException e) {
                    // 우선 순위 파싱에 실패한 경우 기본 우선 순위를 사용
                }
            }

            // 현재 언어의 우선 순위가 저장된 우선 순위보다 높으면 저장
            if (priority > highestPriority) {
                highestPriority = priority;
                preferredLanguage = languageCode;
            }
        }
        return preferredLanguage;
    }

}
