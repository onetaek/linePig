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
    @ManyToOne
    private Product product;
}
