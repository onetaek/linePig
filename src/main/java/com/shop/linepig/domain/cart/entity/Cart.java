package com.shop.linepig.domain.cart.entity;

import com.shop.linepig.domain.common.BaseEntity;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Cart extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cnt;//제품 수량

    @ManyToOne
    private Member member;//회원
    @ManyToOne
    private Product product;//제품
}
