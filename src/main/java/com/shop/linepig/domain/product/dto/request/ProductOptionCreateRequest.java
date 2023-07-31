package com.shop.linepig.domain.product.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
public class ProductOptionCreateRequest {
    private String optionName;
    private List<ProductOptionItemCreateRequest> productOptionItems;
}
