package com.shop.linepig.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.member.entity.SellerExtend;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class SellerExtendResponse {
    private Long id;
    private String name;
    private String value;
    private int sequence;

    public static SellerExtendResponse fromEntity(SellerExtend sellerExtend) {
        if (sellerExtend == null)
            return null;
        return SellerExtendResponse.builder()
                .id(sellerExtend.getId())
                .name(sellerExtend.getName())
                .value(sellerExtend.getValue())
                .sequence(sellerExtend.getSequence())
                .build();
    }
}
