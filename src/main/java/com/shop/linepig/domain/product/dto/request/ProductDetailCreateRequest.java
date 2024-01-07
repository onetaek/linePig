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
    private String nameKo;
    private String nameEn;
    private String valueKo;
    private String valueEn;
    private Integer sequence;

    public static List<ProductDetail> toEntities(List<ProductDetailCreateRequest> productDetailCreateRequests, Product savedProduct) {
        return productDetailCreateRequests
                .stream()
                .map(productDetailCreateRequest -> ProductDetail.builder()
                        .nameKo(productDetailCreateRequest.getNameKo())
                        .nameEn(productDetailCreateRequest.getNameEn())
                        .valueKo(productDetailCreateRequest.getValueKo())
                        .valueEn(productDetailCreateRequest.getValueEn())
                        .sequence(0)//요구사항 있으면 추가 일단 0
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
    }
}
