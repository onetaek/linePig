package com.shop.linepig.domain.member.dto.request;


import lombok.Data;

@Data
public class MemberLoginRequest {

    private String loginId;
    private String password;

}
