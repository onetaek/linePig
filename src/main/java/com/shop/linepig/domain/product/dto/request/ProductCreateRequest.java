package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductCreateRequest {

    private List<UploadBase64EncodedFileRequest> productImages;//제품 이미지
    private String name;//제품이름
    private String nameDescription;//부재
    private int price;//가격
    private String priceDescription;//가격설명
    private int stockNumber;//재고수량
    private List<ProductOptionCreateRequest> productOptions;//옵션
    private List<ProductSpecialCreateRequest> productSpecials;//특이사항
    private List<ProductDetailCreateRequest> productDetails;//세부정보
    private List<UploadBase64EncodedFileRequest> productDetailImages;//제품 상세이미지
    private Long sellerId;//판매자
}
