package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class ProductOptionItem extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String optionValue;//옵션 값

    private Integer sequence = 0;//순서

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductOption productOption;//제품옵션
}
