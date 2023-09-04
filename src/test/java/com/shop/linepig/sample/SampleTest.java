package com.shop.linepig.sample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Transactional
@SpringBootTest
public class SampleTest {


    @Test
    void test() {
        // 헤더를 파싱하여 언어와 우선 순위를 저장할 변수 초기화
        String preferredLanguage = null;
        double highestPriority = 0.0;

        String acceptLanguageHeader = "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7";

        // Accept-Language 헤더 값들을 쉼표로 분리
        String[] languages = acceptLanguageHeader.split(",");

        log.info("languages = {}", Arrays.toString(languages));

        for (String language : languages) {
            String[] parts = language.trim().split(";q=");
            log.info("parts = {}", Arrays.toString(parts));
            String languageCode = parts[0].trim();
            log.info("languageCode = {}",languageCode);
            double priority = 1.0; // 기본 우선 순위 설정 (q=가 없는 경우)

            // 우선 순위(q)가 있는 경우 우선 순위 값을 파싱
            log.info("parts.length = {}",parts.length);
            if (parts.length > 1) {
                try {
                    priority = Double.parseDouble(parts[1].trim());
                    log.info("priority = {}",priority);
                } catch (NumberFormatException e) {
                    // 우선 순위 파싱에 실패한 경우 기본 우선 순위를 사용
                }
            }

            // 현재 언어의 우선 순위가 저장된 우선 순위보다 높으면 저장
            log.info("priority = {}, highestPriority = {}",priority,highestPriority);
            if (priority > highestPriority) {
                highestPriority = priority;
                preferredLanguage = languageCode;
            }
            log.info("preferredLanguage = {}",preferredLanguage);
        }
    }
}
