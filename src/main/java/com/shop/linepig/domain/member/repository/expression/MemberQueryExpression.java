package com.shop.linepig.domain.member.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.shop.linepig.domain.member.entity.QMember.member;

public class MemberQueryExpression {

    public static BooleanExpression eqId(Long id) {
        return id != null ? member.id.eq(id) : null;
    }

}
