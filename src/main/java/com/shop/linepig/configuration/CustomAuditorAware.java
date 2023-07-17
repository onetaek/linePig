package com.shop.linepig.configuration;

import com.shop.linepig.common.constance.SessionConst;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class CustomAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        // 현재 사용자의 ID를 가져와서 반환하는 로직을 구현합니다.
        // 예시로 세션에서 memberId 값을 가져오는 방식을 사용하겠습니다.
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long userId = (Long) request.getSession().getAttribute(SessionConst.MEMBER_ID);
        return Optional.ofNullable(userId);
    }
}
