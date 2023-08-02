package com.shop.linepig.domain.member.dto.request;

import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import lombok.Data;

@Data
public class MemberSnsJoinRequest {

    private String loginId;
    private String name;
    private String email;
    private MemberStatus memberStatus;

}
