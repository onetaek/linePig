package com.shop.linepig.domain.member.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class SellerExtend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String property;
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;
}
