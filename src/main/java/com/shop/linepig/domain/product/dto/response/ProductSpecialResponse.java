package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductSpecial;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductSpecialResponse {

    private Long id;

    private String valueKo;
    private String valueEn;

    private Integer sequence;//순서

    public static ProductSpecialResponse fromEntity(ProductSpecial productSpecial) {
        if (productSpecial == null)
            return null;
        return ProductSpecialResponse.builder()
                .id(productSpecial.getId())
                .valueKo(productSpecial.getValueKo())
                .valueEn(productSpecial.getValueEn())
                .sequence(productSpecial.getSequence())
                .build();
    }
}
