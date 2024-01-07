package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductOption;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductOptionResponse {

    private Long id;

    private String valueKo;//옵션값
    private String valueEn;//옵션값

    private int priceKo;//원
    private double priceEn;//달러

    private String priceDescriptionKo;//가격 상세설명
    private String priceDescriptionEn;

    private int stockQuantity;

    private Integer sequence;//순서
    public static ProductOptionResponse fromEntity(ProductOption productOption) {
        if (productOption == null)
            return null;
        return ProductOptionResponse.builder()
                .id(productOption.getId())
                .valueKo(productOption.getValueKo())
                .valueEn(productOption.getValueEn())
                .priceKo(productOption.getPriceKo())
                .priceEn(productOption.getPriceEn())
                .priceDescriptionKo(productOption.getPriceDescriptionKo())
                .priceDescriptionEn(productOption.getPriceDescriptionEn())
                .stockQuantity(productOption.getStockQuantity())
                .sequence(productOption.getSequence())
                .build();
    }

}
