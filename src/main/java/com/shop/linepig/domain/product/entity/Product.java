package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.common.BaseEntity;
import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.product.entity.enumeration.ProductCategory;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;//제품이름
    private String nameDescription;//부재
    private int price = 0;//가격
    private String priceDescription;//가격 상세설명
    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptionList;//옵션
    @OneToMany(mappedBy = "product")
    private List<ProductSpecial> productSpecialList;//특이사항
    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetailList;//세부 정보
    @OneToMany(mappedBy = "product")
    private List<ProductDetailImage> productDetailImageList;//제품 상세 이미지

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;//카테고리
    private Long memberId;//member테이블의 기본키
    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;//제품 등록은 관리자가 한다.
}
