package com.shop.linepig.domain.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.board.entity.enumeration.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class BoardStatusResponse {
    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private String description;

    public static BoardStatusResponse fromEnum(BoardStatus gender) {
        if (gender == null)
            return null;
        return new BoardStatusResponse(
                gender.getCode(),
                gender.getDisplayValue(),
                gender.getSequence(),
                gender.getDescription());
    }

}
