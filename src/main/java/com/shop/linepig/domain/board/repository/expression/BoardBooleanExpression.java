package com.shop.linepig.domain.board.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.shop.linepig.domain.board.entity.enumeration.BoardCategory;

import static com.shop.linepig.domain.board.entity.QBoard.board;

public class BoardBooleanExpression {

    public static BooleanExpression eqCategory(BoardCategory category) {
        return category == null ? null : board.category.eq(category);
    }

    public static BooleanExpression eqIsTop(Boolean isTop) {
        return isTop == null ? null : board.isTop.eq(isTop);
    }

}
