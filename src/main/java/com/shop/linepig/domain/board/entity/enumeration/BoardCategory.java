package com.shop.linepig.domain.board.entity.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.stream.Stream;
@Getter
public enum BoardCategory {

    NOTICE("NOTICE","NOTICE",10,"구매전에 꼭 읽어 주세요~","Please read it before you buy it"),
    QNA("Q&A","Q&A",20,"사이즈 문의, 입금 배송 문의,코디 문의, 이벤트 문의 등 모든 궁금한 사항들을 남겨주세요~","Please leave all questions such as size inquiries, deposit delivery inquiries, styling inquiries, and event inquiries"),
    FAQ("FAQ","FAQ",30,"자주하는 질문과 답변","frequently asked questions"),
    CONTACT("CONTACT","CONTACT",40,"","");

    private final String code;
    private final String displayValueKo;
    private final String displayValueEn;
    private final Integer sequence;
    private final String descriptionKo;
    private final String descriptionEn;

    BoardCategory(String displayValueKo, String displayValueEn, Integer sequence, String descriptionKo, String descriptionEn) {
        this.code = this.name();
        this.displayValueKo = displayValueKo;
        this.displayValueEn = displayValueEn;
        this.sequence = sequence;
        this.descriptionKo = descriptionKo;
        this.descriptionEn = descriptionEn;
    }

    @JsonCreator
    public static BoardCategory fromCode(String code) {
        return Stream.of(values())
                .filter(enumType -> code.equalsIgnoreCase(enumType.code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 Enum을 식별할 수 없습니다 : " + code));
    }
}
