package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductBasicResponse {
    private Long id;
    private String nameKo;//제품이름
    private String nameEn;

    private String nameDescriptionKo;//제품부재
    private String nameDescriptionEn;

    private String optionKo;//옵션
    private String optionEn;

    private Integer sequence;//순서

    private int representativePriceKo;
    private double representativePriceEn;
    private String representativeImage;//대표 이미지

    private String status;//상태

    private String category;

    public static ProductBasicResponse fromEntity(Product product) {
        if(product == null)
            return null;
        return ProductBasicResponse.builder()
                .id(product.getId())
                .nameKo(product.getNameKo())
                .nameEn(product.getNameEn())
                .nameDescriptionKo(product.getNameDescriptionKo())
                .nameDescriptionEn(product.getNameDescriptionEn())
                .optionKo(product.getOptionKo())
                .optionEn(product.getOptionEn())
                .sequence(0)
                .representativePriceKo(product.getRepresentativePriceKo())
                .representativePriceEn(product.getRepresentativePriceEn())
                .representativeImage(product.getRepresentativeImage())
                .status(product.getStatus().getDisplayValue())
                .category(product.getCategory().getDisplayValue())
                .build();
    }
}
