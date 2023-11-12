package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.cart.entity.CartItem;
import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE PRODCT_OPTION SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class ProductOption extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String valueKo;//옵션값
    private String valueEn;//옵션값

    private int priceKo;//원
    private double priceEn;//달러

    private String priceDescriptionKo;//가격 상세설명
    private String priceDescriptionEn;

    private int stockQuantity;
    @Builder.Default
    private Integer sequence = 0;//순서

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @OneToMany(mappedBy = "productOption")
    private List<CartItem> cartItems;
}
