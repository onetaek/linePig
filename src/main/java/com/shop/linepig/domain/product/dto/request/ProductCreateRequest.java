package com.shop.linepig.domain.product.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductCreateRequest {

    private List<String> productImages;//제품 이미지
    private String name;//제품이름
    private String nameDescription;//부재
    private int price;//가격
    private String priceDescription;//가격설명
    private List<ProductOptionCreateRequest> productOptions;//옵션
    private List<ProductSpecialCreateRequest> productSpecials;//특이사항
    private List<ProductDetailCreateRequest> productDetails;//세부정보
    private List<String> productDetailImages;//제품 상세이미지

    //카테고리는 일단 필요 없음
    //제품 등록자 작업 추가하기
    //제품 관리자 작업 추가하기
}
