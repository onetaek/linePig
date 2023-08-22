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
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;//가격

    private int count;//개수

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;//주문

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;//제품
}
