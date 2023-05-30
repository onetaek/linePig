package com.shop.linepig.domain.member;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;//로그인 아이디
    private String password;//비밀번호
    private String name;//이름
    private String phoneCode;//연락처 국제번호
    private String phoneNumber;//연락처 국제번호
    private String email;//이메일 뒷자리
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;//회원 상태
    private String salt;//난수

}
