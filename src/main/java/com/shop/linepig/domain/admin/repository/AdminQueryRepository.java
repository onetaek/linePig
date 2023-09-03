package com.shop.linepig.domain.admin.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.admin.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.shop.linepig.domain.admin.entity.QAdmin.admin;

@Repository
@RequiredArgsConstructor
public class AdminQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<Admin> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(admin)
                        .where(expressions)
                        .fetchOne());
    }
}
