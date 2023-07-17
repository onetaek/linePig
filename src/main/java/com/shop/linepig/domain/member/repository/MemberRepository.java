package com.shop.linepig.domain.member.repository;

import com.shop.linepig.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {


    public Member findByLoginId(String loginId);

    public Member findByloginIdAndPassword(String loginId, String sha256Pw);

    public boolean existsByLoginId(String loginId);
}
