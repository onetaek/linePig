package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.product.entity.Product;
import com.shop.linepig.domain.product.entity.enumeration.ProductCategory;
import com.shop.linepig.domain.product.entity.enumeration.ProductStatus;
import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ProductCreateRequest {

    private String nameKo;//제품이름
    private String nameEn;//제품이름

    private String nameDescriptionKo;//부재
    private String nameDescriptionEn;//부재

    private String optionKo;
    private String optionEn;

    private Integer sequence;

    private List<ProductOptionCreateRequest> productOptions;//옵션
    private List<ProductSpecialCreateRequest> productSpecials;//특이사항
    private List<ProductDetailCreateRequest> productDetails;//세부정보
    private List<UploadBase64EncodedFileRequest> productImages;//제품 이미지
    private List<UploadBase64EncodedFileRequest> productDetailImages;//제품 상세이미지

    private Long sellerId;

    public static Product toEntity(ProductCreateRequest productCreateRequest,
                                   Admin admin, Seller seller, String representativeImage,
                                   int representativePriceKo, double representativePriceEn) {
        return Product.builder()
                .nameKo(productCreateRequest.getNameKo())
                .nameEn(productCreateRequest.getNameEn())
                .nameDescriptionKo(productCreateRequest.getNameDescriptionKo())
                .nameDescriptionEn(productCreateRequest.getNameDescriptionEn())
                .optionKo(productCreateRequest.getOptionKo())
                .optionEn(productCreateRequest.getOptionEn())
                .representativePriceKo(representativePriceKo)
                .representativePriceEn(representativePriceEn)
                .representativeImage(representativeImage)
                .sequence(0)
                .status(ProductStatus.CREATED)
                .category(ProductCategory.DEFAULT)
                .admin(admin)
                .seller(seller)
                .build();
    }

}
