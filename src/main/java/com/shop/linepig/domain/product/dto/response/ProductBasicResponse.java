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
    private String name;
    private String nameDescription;
    private int price;
    private String priceDescription;
    private Integer sequence;
    private int stockNumber;
    private String status;
    private String category;

    public static ProductBasicResponse fromEntity(Product product) {
        if(product == null)
            return null;
        return ProductBasicResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .nameDescription(product.getNameDescription())
                .price(product.getPrice())
                .priceDescription(product.getPriceDescription())
                .sequence(product.getSequence())
                .stockNumber(product.getStockNumber())
                .status(product.getStatus().getDisplayValue())
                .category(product.getCategory().getDisplayValue())
                .build();
    }
}
