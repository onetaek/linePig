package com.shop.linepig.domain.member.dto.request;

import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.member.entity.SellerExtend;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SellerExtendUpdateRequest {
    private String name;
    private String value;
    private Integer sequence;

    public static List<SellerExtend> toEntities(SellerUpdateRequest request, Seller savedSeller) {
        return request.getSellerExtends().stream()
                .map(sellerExtendCreateRequest -> SellerExtend.builder()
                        .name(sellerExtendCreateRequest.getName())
                        .value(sellerExtendCreateRequest.getValue())
                        .seller(savedSeller)
                        .build()
                )
                .collect(Collectors.toList());
    }
}
