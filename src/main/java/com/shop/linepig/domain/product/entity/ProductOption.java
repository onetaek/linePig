package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class ProductOption extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String optionName;

    @ManyToOne
    private Product product;
}
