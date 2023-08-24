package com.shop.linepig.domain.member.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE SELLER_EXTEND SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class SellerExtend {//판매자의 정보들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String value;
    private int sequence;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    public SellerExtend setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }
}
