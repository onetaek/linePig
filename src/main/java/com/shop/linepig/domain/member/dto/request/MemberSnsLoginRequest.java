package com.shop.linepig.domain.member.dto.request;

import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import com.shop.linepig.domain.member.entity.enumeration.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSnsLoginRequest {

    private String loginId;
    private String email;
    private String name;
    private LocalDateTime joinOn;
    private MemberStatus status;
    private MemberType type;

    public static Member toEntity(MemberSnsLoginRequest request) {
        return Member.builder()
                .loginId(request.getLoginId())
                .email(request.getEmail())
                .name(request.getName())
                .joinOn(request.getJoinOn())
                .status(request.getStatus())
                .type(request.getType())
                .build();
    }
}
