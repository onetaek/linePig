package com.shop.linepig.domain.product.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.linepig.domain.product.entity.ProductOption;
import com.shop.linepig.domain.product.entity.ProductOptionItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProductOptionItemCreateRequest {
    private String optionValue;//옵션 값

    public ProductOptionItemCreateRequest(String optionValue) {
        this.optionValue = optionValue;
    }

    @JsonCreator
    public static ProductOptionItemCreateRequest create(@JsonProperty("optionValue") String optionValue) {
        return new ProductOptionItemCreateRequest(optionValue);
    }
    public static List<ProductOptionItem> toEntities(List<ProductOptionItemCreateRequest> productOptionItemCreateRequests, ProductOption savedProductOption) {
        return productOptionItemCreateRequests
                .stream()
                .map(productOptionItemCreateRequest -> ProductOptionItem.builder()
                        .optionValue(productOptionItemCreateRequest.getOptionValue())
                        .productOption(savedProductOption)
                        .build())
                .collect(Collectors.toList());
    }
}
