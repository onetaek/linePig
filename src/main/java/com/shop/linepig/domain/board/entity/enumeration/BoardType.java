package com.shop.linepig.domain.board.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;
@Getter
public enum BoardType {

    ADMIN("관리자",10,"관리자가 작성한 게시글"),
    USER("사용자",20,"사용자가 작성한 게시글");

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private final String description;

    BoardType(String displayValue, Integer sequence, String description) {
        this.code = this.name();
        this.displayValue = displayValue;
        this.sequence = sequence;
        this.description = description;
    }

    @JsonCreator
    public static BoardType fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
