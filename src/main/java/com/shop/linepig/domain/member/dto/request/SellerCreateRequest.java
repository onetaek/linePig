package com.shop.linepig.domain.member.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SellerCreateRequest {

    private List<SellerExtendCreateRequest> sellerExtends;

}
