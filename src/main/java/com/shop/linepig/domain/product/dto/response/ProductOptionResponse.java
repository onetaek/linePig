package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductOption;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductOptionResponse {

    private Long id;
    private String optionName;
    private Integer sequence;
    private List<ProductOptionItemResponse> productOptionItems;

    public static ProductOptionResponse fromEntity(ProductOption productOption) {
        if (productOption == null)
            return null;
        return ProductOptionResponse.builder()
                .id(productOption.getId())
                .optionName(productOption.getOptionName())
                .sequence(productOption.getSequence())
                .productOptionItems(productOption.getProductOptionItems()
                        .stream()
                        .map(ProductOptionItemResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
