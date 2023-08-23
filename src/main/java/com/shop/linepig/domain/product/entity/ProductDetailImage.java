package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.common.embeddable.UploadFile;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class ProductDetailImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UploadFile uploadFile;

    private Integer sequence = 0;//순서

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public static List<ProductDetailImage> createEntities(List<UploadFile> uploadFiles, Product savedProduct) {
        return uploadFiles
                .stream()
                .map(uploadfile -> ProductDetailImage.builder()
                        .uploadFile(uploadfile)
                        .product(savedProduct)
                        .build()
                ).collect(Collectors.toList());
    }
}
