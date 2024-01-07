package com.shop.linepig.common.advice;

import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.admin.controller.AdminController;
import com.shop.linepig.domain.admin.dto.response.AdminResponse;
import com.shop.linepig.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 로그인한 관리자 데이터(admin)을 model에 담는 컨트롤러
 */
@ControllerAdvice(basePackageClasses = AdminController.class )
@RequiredArgsConstructor
public class LoginAdminModelAttributeAdvice {

    private final AdminService adminService;

    @ModelAttribute
    public void addAdminModelAttributes(Model model, HttpServletRequest request) {

        // 현재 요청의 핸들러 메서드 정보를 가져옵니다.
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);

        // 현재 요청이 login 메서드인 경우에는 addAdminModelAttributes 메서드를 실행하지 않습니다.
        if (handlerMethod != null && handlerMethod.getMethod().getName().equals("loginPage")) {
            return;
        }

        // 세션에서 관리자 아이디 가져오기
        Long id = (Long) request.getSession().getAttribute(SessionConst.ADMIN_ID);
        AdminResponse findAdmin = adminService.findById(id);
        model.addAttribute("admin",findAdmin);
    }

}
