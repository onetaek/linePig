package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.product.entity.Product;
import com.shop.linepig.domain.product.entity.ProductDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDetailCreateRequest {
    private String name;
    private String value;

    public static List<ProductDetail> toEntities(List<ProductDetailCreateRequest> productDetailCreateRequests, Product savedProduct) {
        return productDetailCreateRequests
                .stream()
                .map(productDetailCreateRequest -> ProductDetail.builder()
                        .name(productDetailCreateRequest.getName())
                        .value(productDetailCreateRequest.getValue())
                        .sequence(0)//요구사항 있으면 추가 일단 0
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
    }
}
