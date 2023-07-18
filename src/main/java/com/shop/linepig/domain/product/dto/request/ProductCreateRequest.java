package com.shop.linepig.domain.product.dto.request;

import com.shop.linepig.domain.product.entity.ProductDetailImage;
import com.shop.linepig.domain.product.entity.ProductOption;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter @Setter
@Slf4j
public class ProductCreateRequest {

    private String name;

    private List<ProductImageCreateRequest> productImageList;
    private List<ProductDetailImageCreateRequest> productDetailImageList;
    private List<ProductOptionCreateRequest> productOptionList;
    private List<ProductDetailCreateRequest> productDetail;


}
