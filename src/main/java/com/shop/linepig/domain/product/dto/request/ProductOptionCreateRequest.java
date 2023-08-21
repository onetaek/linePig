package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.product.entity.Product;
import com.shop.linepig.domain.product.entity.ProductOption;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductOptionCreateRequest {
    private String optionName;
    private List<ProductOptionItemCreateRequest> productOptionItems;

    public static ProductOption toEntity(ProductOptionCreateRequest productOptionCreateRequest, Product savedProduct) {
        return ProductOption.builder()
                .optionName(productOptionCreateRequest.getOptionName())
                .product(savedProduct)
                .build();
    }

    public static List<ProductOption> toEntities(List<ProductOptionCreateRequest> productOptionCreateRequests, Product savedProduct) {
        return productOptionCreateRequests
                .stream()
                .map(productOptionCreateRequest -> ProductOption.builder()
                        .optionName(productOptionCreateRequest.getOptionName())
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
    }

}
