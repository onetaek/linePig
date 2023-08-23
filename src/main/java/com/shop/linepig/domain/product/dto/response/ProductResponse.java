package com.shop.linepig.domain.product.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.member.dto.response.SellerResponse;
import com.shop.linepig.domain.product.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private String nameDescription;
    private int price;
    private String priceDescription;
    private Integer sequence;
    private int stockNumber;
    private String status;
    private String category;
    private SellerResponse seller;
    private List<ProductImageResponse> productImages;
    private List<ProductOptionResponse> productOptions;
    private List<ProductSpecialResponse> productSpecials;
    private List<ProductDetailResponse> productDetails;
    private List<ProductDetailImageResponse> productDetailImages;

    public static ProductResponse fromEntity(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .nameDescription(product.getNameDescription())
                .price(product.getPrice())
                .priceDescription(product.getPriceDescription())
                .sequence(product.getSequence())
                .stockNumber(product.getStockNumber())
                .status(product.getStatus().getDisplayValue())
                .category(product.getCategory().getDisplayValue())
                .seller(SellerResponse.fromEntity(product.getSeller()))
                .productImages(product.getProductImages()
                        .stream()
                        .map(ProductImageResponse::fromEntity)
                        .collect(Collectors.toList()))
                .productOptions(product.getProductOptions()
                        .stream()
                        .map(ProductOptionResponse::fromEntity)
                        .collect(Collectors.toList()))
                .productSpecials(product.getProductSpecials()
                        .stream()
                        .map(ProductSpecialResponse::fromEntity)
                        .collect(Collectors.toList()))
                .productDetails(product.getProductDetails()
                        .stream()
                        .map(ProductDetailResponse::fromEntity)
                        .collect(Collectors.toList()))
                .productDetailImages(product.getProductDetailImages()
                        .stream()
                        .map(ProductDetailImageResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}
