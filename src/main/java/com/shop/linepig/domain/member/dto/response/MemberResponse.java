package com.shop.linepig.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.common.util.DateTimeFormatterUtil;
import com.shop.linepig.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class MemberResponse {

    private Long id;
    private String loginId;
    private String email;
    private String joinOn;
    private String phoneCode;
    private String phoneNumber;
    private String memberStatus;
    private String name;
    private String gender;

    public static MemberResponse fromEntity(Member member) {
        if(member == null)
            return null;
        return MemberResponse.builder()
                .id(member.getId())
                .loginId(member.getLoginId())
                .email(member.getEmail())
                .joinOn(DateTimeFormatterUtil.formatWithSeconds(member.getJoinOn()))
                .phoneCode(member.getPhoneCode())
                .phoneNumber(member.getPhoneNumber())
                .memberStatus(member.getStatus())
                .name(member.getName())
                .gender(member.getGender())
                .build();
    }
}
