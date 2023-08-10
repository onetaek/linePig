package com.shop.linepig.domain.member.entity;

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
public class Seller extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "seller")
    private List<SellerExtend> sellerExtends = new ArrayList<>();

    @OneToOne
    private Member member;

    public Seller update(List<SellerExtend> sellerExtends) {
        this.sellerExtends = sellerExtends;
        return this;
    }

    public void addSellerExtends(List<SellerExtend> sellerExtends) {
        this.sellerExtends = sellerExtends;
    }


    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", sellerExtends=" + (sellerExtends != null ? sellerExtends.toString() : "null") +
                ", member=" + (member != null ? member.toString() : "null") +
                '}';
    }
}
