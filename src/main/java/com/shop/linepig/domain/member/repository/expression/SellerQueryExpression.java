package com.shop.linepig.domain.member.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.shop.linepig.domain.member.entity.QSeller.seller;

public class SellerQueryExpression {

    public static BooleanExpression eqId(Long id) {
        return id != null ? seller.id.eq(id) : null;
    }

}
