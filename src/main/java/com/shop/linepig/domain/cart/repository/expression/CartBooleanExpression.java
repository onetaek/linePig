package com.shop.linepig.domain.cart.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.shop.linepig.domain.cart.entity.QCart.cart;

public class CartBooleanExpression {

    public static BooleanExpression eqMemberId(Long memberId) {
        return memberId == null ? null : cart.member.id.eq(memberId);
    }

    public static BooleanExpression eqId(Long id) {
        return id == null ? null : cart.id.eq(id);
    }
}
