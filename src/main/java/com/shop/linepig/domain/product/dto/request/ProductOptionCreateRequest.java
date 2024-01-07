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
    private String valueKo;
    private String valueEn;

    private int priceKo;
    private double priceEn;

    private String priceDescriptionKo;
    private String priceDescriptionEn;

    private int stockQuantity;

    private Integer sequence;

    public static List<ProductOption> toEntities(List<ProductOptionCreateRequest> productOptionCreateRequests, Product savedProduct) {
        return productOptionCreateRequests.stream()
                        .map(productOptionCreateRequest -> ProductOption.builder()
                                .valueKo(productOptionCreateRequest.getValueKo())
                                .valueEn(productOptionCreateRequest.getValueEn())
                                .priceKo(productOptionCreateRequest.getPriceKo())
                                .priceEn(productOptionCreateRequest.getPriceEn())
                                .priceDescriptionKo(productOptionCreateRequest.getPriceDescriptionKo())
                                .priceDescriptionEn(productOptionCreateRequest.getPriceDescriptionEn())
                                .stockQuantity(productOptionCreateRequest.getStockQuantity())
                                .sequence(0)
                                .product(savedProduct)
                                .build())
                .collect(Collectors.toList());
    }

}
