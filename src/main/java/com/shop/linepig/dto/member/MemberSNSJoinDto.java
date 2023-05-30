package com.shop.linepig.dto.member;

import com.shop.linepig.domain.member.MemberStatus;
import lombok.Data;

@Data
public class MemberSNSJoinDto {

    private String loginId;
    private String name;
    private String email;
    private MemberStatus memberStatus;

}
