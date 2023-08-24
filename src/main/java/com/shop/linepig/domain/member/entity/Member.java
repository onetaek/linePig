package com.shop.linepig.domain.member.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import com.shop.linepig.domain.member.entity.enumeration.Gender;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@EnableJpaAuditing
@Entity
@SQLDelete(sql = "UPDATE MEMBER SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private MemberStatus status;//회원 상태

    private String salt;//난수

    private LocalDateTime joinOn;//회원가입 시각

    @OneToOne(mappedBy = "member")
    private Seller seller;

    public Member setStatus(MemberStatus status) {
        this.status = status;
        return this;
    }

    public String getStatus() {
        return this.status.getDisplayValue();
    }

    public String getGender() {
        if (this.gender == null)
            return null;
        return this.gender.getDisplayValue();
    }

    public Member updateStatus(MemberStatus status) {
        this.status = status;
        return this;
    }
}
