package com.shop.linepig.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.member.entity.Seller;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class SellerResponse {

    private Long id;
    private MemberResponse member;
    private List<SellerExtendResponse> sellerExtends;

    public static SellerResponse fromEntity(Seller seller) {
        if (seller == null)
            return null;
        return SellerResponse.builder()
                .id(seller.getId())
                .member(MemberResponse.fromEntity(seller.getMember()))
                .sellerExtends(seller.getSellerExtends()
                        .stream()
                        .map(SellerExtendResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
