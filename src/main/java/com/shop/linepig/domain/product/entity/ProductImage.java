package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.product.entity.embeddable.UploadFile;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class ProductImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private UploadFile uploadFile;
    private int sequence = 0;//순서
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
