package com.shop.linepig.domain.order.entity;

import com.shop.linepig.domain.common.BaseEntity;
import com.shop.linepig.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class OrderDetail extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;//가격
    private int cnt;//개수

    @ManyToOne
    private TotalOrder totalOrder;//주문
    @ManyToOne
    private Product product;//제품
}
