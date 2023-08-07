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
    private List<SellerExtend> sellers = new ArrayList<>();

    @OneToOne
    private Member member;
}
