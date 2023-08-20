package com.shop.linepig.domain.member.entity.enumeration;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;
@Getter
public enum MemberStatus {

    NORMAL("일반",0,""),
    BAN("차단",10,""),
    SELLER("판매자",20,"");

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private final String description;

    MemberStatus(String displayValue, Integer sequence, String description) {
        this.code = this.name();
        this.displayValue = displayValue;
        this.sequence = sequence;
        this.description = description;
    }

    @JsonCreator
    public static MemberStatus fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
