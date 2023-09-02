package com.shop.linepig.domain.product.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ProductStatus {

    CREATED("생성",10,""),
    WAIT("대기",20,""),
    SELL("판매중",30,""),
    SOLD_OUT("재고 소진", 40,""),
    DELETED("삭제",50,"");

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private final String description;

    ProductStatus(String displayValue, Integer sequence, String description) {
        this.code = this.name();
        this.displayValue = displayValue;
        this.sequence = sequence;
        this.description = description;
    }

    @JsonCreator
    public static ProductStatus fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
