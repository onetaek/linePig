package com.shop.linepig.domain.product.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;
@Getter
public enum UnitOfCurrency {
    KRW("원",0,"대한민국: 원"),
    USD("$",10,"미국: 달러"),
    CNY("¥",20,"중국: 위안화");

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private final String description;

    UnitOfCurrency(String displayValue, Integer sequence, String description) {
        this.code = this.name();
        this.displayValue = displayValue;
        this.sequence = sequence;
        this.description = description;
    }

    @JsonCreator
    public static UnitOfCurrency fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
