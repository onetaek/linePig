package com.shop.linepig.domain.admin.repository;

import com.shop.linepig.domain.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {


    Admin findByLoginId(String loginId);

    Admin findByLoginIdAndPassword(String loginId, String password);
}
