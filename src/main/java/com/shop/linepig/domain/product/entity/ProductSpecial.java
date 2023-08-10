package com.shop.linepig.domain.product.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class ProductSpecial {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;
    private int sequence = 0;//순서

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
