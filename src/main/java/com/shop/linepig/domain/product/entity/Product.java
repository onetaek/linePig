package com.shop.linepig.domain.product.entity;

import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.product.entity.enumeration.ProductCategory;
import com.shop.linepig.domain.product.entity.enumeration.ProductStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@DynamicInsert
@DynamicUpdate
@Getter
@Entity
@SQLDelete(sql = "UPDATE PRODCT SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Product extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameKo;//제품이름
    private String nameEn;

    private String nameDescriptionKo;//제품부재
    private String nameDescriptionEn;

    private String optionKo;//옵션
    private String optionEn;

    //Product 목록 조회할 때, 조인을 하지 않기 위해서 가격, 이미지를 저장
    private int representativePriceKo;
    private double representativePriceEn;
    private String representativeImage;//대표 이미지

    private Integer sequence = 0;//순서

    @Enumerated(EnumType.STRING)
    private ProductStatus status;//상태

    @Enumerated(EnumType.STRING)
    private ProductCategory category = ProductCategory.DEFAULT;//카테고리(확정을 고려해서 일단 DEFAULT만 넣음)

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;//제품 등록은 관리자가 한다.

    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;//판매자

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions = new ArrayList<>();//옵션

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();//제품이미지

    @OneToMany(mappedBy = "product")
    private List<ProductSpecial> productSpecials = new ArrayList<>();//특이사항

    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails = new ArrayList<>();//세부 정보

    @OneToMany(mappedBy = "product")
    private List<ProductDetailImage> productDetailImages = new ArrayList<>();//제품 상세 이미지


}
