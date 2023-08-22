package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.product.entity.Product;
import com.shop.linepig.domain.product.entity.ProductImage;
import com.shop.linepig.domain.product.entity.embeddable.UploadFile;
import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductImageCreateRequest {

    private Integer sequence;
    private String fileBase64;
    private String fileName;
    private String contentType;

    public static UploadBase64EncodedFileRequest toUploadFileRequest(ProductImageCreateRequest productImageCreateRequest) {
        return UploadBase64EncodedFileRequest.builder()
                .sequence(productImageCreateRequest.getSequence())
                .fileBase64(productImageCreateRequest.getFileBase64())
                .fileName(productImageCreateRequest.getFileName())
                .contentType(productImageCreateRequest.getContentType())
                .build();
    }

    public static List<ProductImage> toEntities(List<UploadFile> uploadFiles, Product savedProduct) {
        return uploadFiles.stream()
                .map(uploadFile -> ProductImage.builder()
                        .uploadFile(uploadFile)
                        .product(savedProduct)
                        .build())
                .collect(Collectors.toList());
    }
}
