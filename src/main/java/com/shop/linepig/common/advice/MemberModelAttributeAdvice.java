package com.shop.linepig.common.advice;

import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.member.dto.response.MemberBasicResponse;
import com.shop.linepig.domain.member.service.MemberService;
import com.shop.linepig.domain.product.controller.ProductController;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackageClasses = ProductController.class )
@RequiredArgsConstructor
public class MemberModelAttributeAdvice {

    private final MemberService memberService;

    public void addBasicMemberModelAttributes(Model model, HttpServletRequest request) {

        // 현재 요청의 핸들러 메서드 정보를 가져옵니다.
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);

        // 현재 요청이 login 메서드인 경우에는 addAdminModelAttributes 메서드를 실행하지 않습니다.
        if (handlerMethod != null && handlerMethod.getMethod().getName().equals("loginPage")) {
            return;
        }

        // 세션에서 멤버 아이디 가져오기
        Long memberId = (Long) request.getSession().getAttribute(SessionConst.MEMBER_ID);
        MemberBasicResponse findMember = memberService.findBasicById(memberId);
        model.addAttribute("loginMember",findMember);
    }
}
