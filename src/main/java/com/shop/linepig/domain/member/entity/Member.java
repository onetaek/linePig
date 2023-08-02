package com.shop.linepig.domain.member.entity;

import com.shop.linepig.domain.common.BaseEntity;
import com.shop.linepig.domain.member.entity.enumeration.Gender;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@EnableJpaAuditing
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;//로그인 아이디
    private String password;//비밀번호
    private String name;//이름
    private String email;//이메일 뒷자리
    private String phoneNumber;//연락처 국제번호
    private String phoneCode;//연락처 국제번호
    @Enumerated(EnumType.STRING)
    private Gender gender;//성별
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;//회원 상태
    private String salt;//난수
    @OneToOne
    private Seller seller;

    public String getMemberStatus() {
        return this.memberStatus.toString();
    }

    public String getGender() {
        if (this.gender == null)
            return null;
        return this.gender.toString();
    }
}
