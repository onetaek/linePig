package com.shop.linepig.domain.member.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MemberUpdateRequest {
    @NotNull
    private String status;

}
