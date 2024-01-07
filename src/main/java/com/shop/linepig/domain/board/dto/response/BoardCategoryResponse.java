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
    private final String displayValueKo;
    private final String displayValueEn;
    private final Integer sequence;
    private final String descriptionKo;
    private final String descriptionEn;

    public static BoardCategoryResponse fromEnum(BoardCategory enumeration) {
        if (enumeration == null)
            return null;
        return new BoardCategoryResponse(
                enumeration.getCode(),
                enumeration.getDisplayValueKo(),
                enumeration.getDisplayValueEn(),
                enumeration.getSequence(),
                enumeration.getDescriptionKo(),
                enumeration.getDescriptionEn());
    }

}
