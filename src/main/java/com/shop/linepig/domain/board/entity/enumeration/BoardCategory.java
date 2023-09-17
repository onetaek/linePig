package com.shop.linepig.domain.board.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;
@Getter
public enum BoardCategory {

    NOTICE("NOTICE",10,"공지사항"),
    QNA("Q&A",20,"질문과 답변"),
    CONTACT("CONTACT",30,""),
    FAQ("FAQ",40,"자주하는 질문");

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private final String description;

    BoardCategory(String displayValue, Integer sequence, String description) {
        this.code = this.name();
        this.displayValue = displayValue;
        this.sequence = sequence;
        this.description = description;
    }

    @JsonCreator
    public static BoardCategory fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
