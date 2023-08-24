package com.shop.linepig.domain.cart.entity;

import com.shop.linepig.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE CART_ITEM SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


}
