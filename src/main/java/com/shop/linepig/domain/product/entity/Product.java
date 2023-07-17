package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.common.BaseEntity;
import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.product.entity.enumeration.ProductCategory;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//상품명
    private int price = 0;//가격
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;//카테고리
    private String description;//비고

    private Long memberId;//member테이블의 기본키

    @ManyToOne
    private Admin admin;//제품 등록은 관리자가 한다.
}
