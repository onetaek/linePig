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
    private String phone1;//연락처 국제번호
    private String phone2;//연락처 앞번호
    private String phone3;//연락처 중간번호
    private String phone4;//연락처 끝번호
    private String email1;//이메일 앞자리
    private String email2;//이메일 뒷자리
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;//회원 상태
    private String salt;//난수

}
