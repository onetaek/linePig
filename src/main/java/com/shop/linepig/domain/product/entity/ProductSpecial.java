package com.shop.linepig.domain.product.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE PRODCT_SPECIAL SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class ProductSpecial {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private Integer sequence = 0;//순서

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
