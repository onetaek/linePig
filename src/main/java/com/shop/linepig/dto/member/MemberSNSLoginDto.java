package com.shop.linepig.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSNSLoginDto {

    private String loginId;
    private String email;
    private String name;

}
