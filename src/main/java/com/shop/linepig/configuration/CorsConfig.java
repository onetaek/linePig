package com.shop.linepig.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Value("${cors.access.uri}")
    private String corsAccessUri;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(corsAccessUri); // 허용할 출처
        config.addAllowedHeader("*"); // 허용할 헤더
        config.addAllowedMethod("*"); // 허용할 HTTP 메서드
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
