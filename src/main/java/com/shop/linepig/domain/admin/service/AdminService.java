package com.shop.linepig.domain.admin.service;

import com.shop.linepig.common.util.PasswdEncry;
import com.shop.linepig.domain.admin.dto.request.AdminLoginRequest;
import com.shop.linepig.domain.admin.dto.response.AdminResponse;
import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.admin.repository.AdminQueryRepository;
import com.shop.linepig.domain.admin.repository.AdminRepository;
import com.shop.linepig.domain.admin.repository.expression.AdminQueryExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminQueryRepository adminQueryRepository;

    public Long login(AdminLoginRequest request) {

        String loginId = request.getLoginId();
        String password = request.getPassword();

        Admin findAdminByLoginId = adminQueryRepository.findOne(AdminQueryExpression.eqLoginId(loginId)).orElseGet(null);
        if(findAdminByLoginId == null) {
            //예외처리
            return null;
        }

        String salt = findAdminByLoginId.getSalt();

        PasswdEncry passwdEncry = new PasswdEncry();

        String SHA256Pw = passwdEncry.getEncry(password, salt);

        Admin findAdmin = adminQueryRepository.findOne(AdminQueryExpression.eqLoginId(loginId), AdminQueryExpression.eqPassword(SHA256Pw)).orElseGet(null);

        if(findAdmin == null){
            //예외처리
            return null;
        }

        return findAdmin.getId();
    }

    public AdminResponse findById(Long id) {
        Admin findAdmin = adminRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("admin을 찾을 수 없습니다."));
        log.info("findAdmin = {}",findAdmin);
        return AdminResponse.fromEntity(findAdmin);
    }

}
