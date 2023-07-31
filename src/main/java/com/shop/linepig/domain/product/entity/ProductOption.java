package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class ProductOption extends BaseEntity {

    @Id @GeneratedValue
    private Long id;
    private String optionName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @OneToMany(mappedBy = "productOption")
    private List<ProductOptionItem> productOptionItems = new ArrayList<>();
}
