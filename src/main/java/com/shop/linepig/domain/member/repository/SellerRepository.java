package com.shop.linepig.domain.member.repository;

import com.shop.linepig.domain.member.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Seller findByMemberId(Long memberId);
}
