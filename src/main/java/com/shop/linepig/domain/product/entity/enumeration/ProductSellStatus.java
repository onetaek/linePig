package com.shop.linepig.domain.product.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ProductSellStatus {
    SELL("판매중",0,""),
    SOLD_OUT("재고 소진", 10,"");

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private final String description;

    ProductSellStatus(String displayValue, Integer sequence, String description) {
        this.code = this.name();
        this.displayValue = displayValue;
        this.sequence = sequence;
        this.description = description;
    }

    @JsonCreator
    public static ProductSellStatus fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
