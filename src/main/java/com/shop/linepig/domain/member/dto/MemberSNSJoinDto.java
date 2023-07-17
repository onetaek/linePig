package com.shop.linepig.domain.member.dto;

import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import lombok.Data;

@Data
public class MemberSNSJoinDto {

    private String loginId;
    private String name;
    private String email;
    private MemberStatus memberStatus;

}
