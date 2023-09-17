package com.shop.linepig.domain.board.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class BoardCategoryResponse {

    private final String code;
    private final String displayValue;
    private final Integer sequence;
    private String description;

    public static BoardCategoryResponse fromEnum(BoardCategory gender) {
        if (gender == null)
            return null;
        return new BoardCategoryResponse(
                gender.getCode(),
                gender.getDisplayValue(),
                gender.getSequence(),
                gender.getDescription());
    }

}
