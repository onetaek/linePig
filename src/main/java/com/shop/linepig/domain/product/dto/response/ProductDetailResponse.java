package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductDetail;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductDetailResponse {
    private Long id;

    private String nameKo;//상세정보 항목 이름
    private String nameEn;

    private String valueKo;//상세 정보 항목 값
    private String valueEn;

    private Integer sequence;// 순서

    public static ProductDetailResponse fromEntity(ProductDetail productDetail) {
        if (productDetail == null)
            return null;
        return ProductDetailResponse.builder()
                .id(productDetail.getId())
                .nameKo(productDetail.getNameKo())
                .nameEn(productDetail.getNameEn())
                .valueKo(productDetail.getValueKo())
                .valueEn(productDetail.getValueEn())
                .sequence(productDetail.getSequence())
                .build();
    }
}
