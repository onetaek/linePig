package com.shop.linepig.domain.admin.api;

import com.shop.linepig.common.constance.SessionConst;
import com.shop.linepig.domain.admin.dto.request.AdminLoginRequest;
import com.shop.linepig.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

    @PostMapping("/admins/login")
    public ResponseEntity<?> login(@Validated @RequestBody AdminLoginRequest request, HttpServletRequest httpServletRequest) {

        Long adminId = adminService.login(request);

        if (adminId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute(SessionConst.ADMIN_ID, adminId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/admins/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
