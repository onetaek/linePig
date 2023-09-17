package com.shop.linepig.domain.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.board.entity.enumeration.BoardType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class BoardTypeResponse {
    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private String description;

    public static BoardTypeResponse fromEnum(BoardType gender) {
        if (gender == null)
            return null;
        return new BoardTypeResponse(
                gender.getCode(),
                gender.getDisplayValue(),
                gender.getSequence(),
                gender.getDescription());
    }

}
