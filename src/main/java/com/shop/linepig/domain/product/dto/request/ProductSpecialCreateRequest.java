package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.product.entity.Product;
import com.shop.linepig.domain.product.entity.ProductSpecial;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductSpecialCreateRequest {
    private String valueKo;
    private String valueEn;

    private Integer sequence;

    public static List<ProductSpecial> toEntities(List<ProductSpecialCreateRequest> productSpecialCreateRequests, Product savedProduct) {
        return productSpecialCreateRequests.stream()
                .map(productSpecialCreateRequest -> ProductSpecial.builder()
                        .valueKo(productSpecialCreateRequest.getValueKo())
                        .valueEn(productSpecialCreateRequest.getValueEn())
                        .sequence(0)
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
    }
}
