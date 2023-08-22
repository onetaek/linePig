package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductOptionItem;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductOptionItemResponse {

    private Long id;
    private String optionValue;
    private Integer sequence;

    public static ProductOptionItemResponse fromEntity(ProductOptionItem productOptionItem) {
        if (productOptionItem == null)
            return null;
        return ProductOptionItemResponse.builder()
                .id(productOptionItem.getId())
                .optionValue(productOptionItem.getOptionValue())
                .sequence(productOptionItem.getSequence())
                .build();
    }

}
