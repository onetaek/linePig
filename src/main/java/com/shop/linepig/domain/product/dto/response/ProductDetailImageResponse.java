package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductDetailImage;
import com.shop.linepig.domain.common.embeddable.UploadFile;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductDetailImageResponse {
    private Long id;
    private UploadFile uploadFile;

    public static ProductDetailImageResponse fromEntity(ProductDetailImage productDetailImage) {
        if(productDetailImage == null)
            return null;
        return ProductDetailImageResponse.builder()
                .id(productDetailImage.getId())
                .uploadFile(productDetailImage.getUploadFile())
                .build();
    }
}
