package com.shop.linepig.domain.cart.dto.response;

import lombok.Data;

@Data
public class CartItemResponse {
    //cartItem
    private Long cartItemId;
    private int count;

    //member
    private Long memberId;
    private String memberLoginId;
    private String memberName;

    //product
    private String productNameKo;
    private String productNameEn;
    private String productNameDescriptionKo;
    private String productNameDescriptionEn;
    private String productOptionKo;
    private String productOptionEn;
    private double productRepresentativePriceKo;
    private double productRepresentativePriceEn;
    private String productRepresentativeImage;

    //productOption
    private String productOptionValueKo;
    private String productOptionValueEn;
    private int productOptionPriceKo;
    private double productOptionPriceEn;
    private int stockQuantity;
    private Integer sequence;
}
