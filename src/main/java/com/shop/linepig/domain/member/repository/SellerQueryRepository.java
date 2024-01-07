package com.shop.linepig.domain.member.repository;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.member.entity.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shop.linepig.domain.member.entity.QMember.member;
import static com.shop.linepig.domain.member.entity.QSeller.seller;
import static com.shop.linepig.domain.member.entity.QSellerExtend.sellerExtend;

@Repository
@RequiredArgsConstructor
public class SellerQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Seller> findAllWithFetchJoin(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(seller)
                .distinct()
                .join(seller.sellerExtends, sellerExtend).fetchJoin()
                .join(seller.member, member).fetchJoin()
                .where(expressions)
                .fetch();
    }

    public Optional<Seller> findOneWithFetchJoin(BooleanExpression... expressions) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(seller)
                        .join(seller.sellerExtends, sellerExtend).fetchJoin()
                        .where(expressions)
                        .fetchOne());
    }

}
