package com.shop.linepig.configuration;

import com.shop.linepig.common.argumentresolver.LoginAdminArgumentResolver;
import com.shop.linepig.common.argumentresolver.LoginMemberArgumentResolver;
import com.shop.linepig.common.interceptor.AdminCheckInterceptor;
import com.shop.linepig.common.interceptor.EncodingInterceptor;
import com.shop.linepig.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/members/join","/members/login",
                        "/admins/**","/api/admins/**","/","/products/*","/boards/*",
                        "/error","/api/**","/oauth/**","/css/**",
                        "/js/**", "/img/**","/*.ico","/lib/**")
                .excludePathPatterns("/test/**");

        registry.addInterceptor(new AdminCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/admins/login",
                        "/error","/api/**","/oauth/**","/css/**",
                        "/js/**", "/img/**","/*.ico","/lib/**")
                .excludePathPatterns("/test/**");

        registry.addInterceptor(new EncodingInterceptor())
                .order(3)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/js/**","/img/**","/*.ico","/html/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
        resolvers.add(new LoginAdminArgumentResolver());
    }
}
