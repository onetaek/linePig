package com.shop.linepig.common.interceptor;

import com.shop.linepig.common.constance.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("관리자 체크 인터셉터 실행 {}",requestURI);

        if (requestURI.startsWith("/admins") || requestURI.startsWith("/api/admins")) {
            log.info("관리자 관련 요청 감지");
            HttpSession session = request.getSession();
            if(session == null || session.getAttribute(SessionConst.ADMIN_ID) == null) {
                log.info("미인증 관리자 요청");
                response.sendRedirect("/admins/login");
                return false;
            }
        }
        return true;
    }
}
