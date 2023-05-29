package com.shop.linepig.dto.member;

import com.shop.linepig.domain.member.MemberStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Slf4j
@Data
public class MemberJoinDto {

    //프론트 단에서 입력받는 값
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,10}$")
    private String loginId;//아이디

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$")
    private String password;//비밀번호
    private String passwordCheck;//비밀번호 체크
    @NotBlank
    private String name;//이름
    @NotBlank
    private String phone1;//연락처 국제 번호
    @NotBlank
    private String phone2;//연락처 앞번호
    @NotBlank
    private String phone3;//연락처 중간번호
    private String phone4;//연락처 끝번호
    @NotBlank
    private String email1;//이메일 앞자리
    @NotBlank
    private String email2;//이메일 뒷자리
    //백엔드 단에서 추가해주는 값
    private MemberStatus status;//회원상태
    private String salt;//난수

}
