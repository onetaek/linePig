package com.shop.linepig.domain.member.dto.request;

import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Slf4j
@Data
public class MemberJoinRequest {

    //프론트 단에서 입력받는 값
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,10}$")
    private String loginId;//아이디

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$")
    private String password;//비밀번호
    private String passwordCheck;//비밀번호 체크
    @NotBlank
    private String name;//이름
    private String phoneCode;
    private String phoneNumber;
    @NotBlank
    private String email;//이메일 앞자리
    //백엔드 단에서 추가해주는 값
    private MemberStatus status;//회원상태
    private String salt;//난수

    public static Member toEntity(MemberJoinRequest memberJoinRequest) {
        return Member.builder()
                .loginId(memberJoinRequest.getLoginId())
                .password(memberJoinRequest.getPassword())
                .name(memberJoinRequest.getName())
                .phoneCode(memberJoinRequest.getPhoneCode())
                .phoneNumber(memberJoinRequest.getPhoneNumber())
                .email(memberJoinRequest.getEmail())
                .status(MemberStatus.NORMAL)
                .salt(memberJoinRequest.getSalt())
                .joinOn(LocalDateTime.now())
                .build();
    }

}
