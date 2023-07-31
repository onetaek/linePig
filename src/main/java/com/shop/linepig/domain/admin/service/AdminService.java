package com.shop.linepig.domain.admin.service;

import com.shop.linepig.common.util.PasswdEncry;
import com.shop.linepig.domain.admin.dto.request.AdminLoginRequest;
import com.shop.linepig.domain.admin.dto.response.AdminResponse;
import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public Long login(AdminLoginRequest request) {

        Admin findAdminByLoginId = adminRepository.findByLoginId(request.getLoginId());
        if(findAdminByLoginId == null) {
            //예외처리
            return null;
        }

        String salt = findAdminByLoginId.getSalt();

        PasswdEncry passwdEncry = new PasswdEncry();

        String password = request.getPassword();

        String SHA256Pw = passwdEncry.getEncry(password, salt);

        Admin findAdmin = adminRepository.findByLoginIdAndPassword(request.getLoginId(), SHA256Pw);

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
