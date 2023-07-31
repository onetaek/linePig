package com.shop.linepig.domain.admin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.admin.entity.Admin;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class AdminResponse {

    private Long id;
    private String loginId;
    private String name;
    private String profileImgLink;

    public static AdminResponse fromEntity(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .loginId(admin.getLoginId())
                .name(admin.getName())
                .profileImgLink(admin.getProfileImgLink())
                .build();
    }
}
