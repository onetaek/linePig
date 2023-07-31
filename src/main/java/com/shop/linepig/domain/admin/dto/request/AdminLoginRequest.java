package com.shop.linepig.domain.admin.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdminLoginRequest {

    @NotNull
    private String loginId;
    @NotNull
    private String password;

}
