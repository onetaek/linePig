package com.shop.linepig.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.member.dto.response.MemberBasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.shop.linepig.domain.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MemberBasicResponse findBasicOne(BooleanExpression... expressions) {
        return jpaQueryFactory.select(
                Projections.bean(MemberBasicResponse.class,
                        member.id,
                        member.name))
                .from(member)
                .where(expressions)
                .fetchOne();
    }

}
