package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.product.entity.ProductImage;
import com.shop.linepig.domain.common.embeddable.UploadFile;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductImageResponse {
    private Long id;
    private UploadFile uploadFile;
    private Integer sequence;

    public static ProductImageResponse fromEntity(ProductImage productImage) {
        if (productImage == null)
            return null;
        return ProductImageResponse.builder()
                .id(productImage.getId())
                .uploadFile(productImage.getUploadFile())
                .sequence(productImage.getSequence())
                .build();
    }
}
