package com.shop.linepig.domain.member.dto.request;

import lombok.Data;

@Data
public class SellerExtendCreateRequest {
    private String name;
    private String value;
    private Integer sequence;
}
