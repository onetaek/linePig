package com.shop.linepig.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shop.linepig.domain.admin.entity.QAdmin.admin;
import static com.shop.linepig.domain.member.entity.QSeller.seller;
import static com.shop.linepig.domain.product.entity.QProduct.product;
import static com.shop.linepig.domain.product.entity.QProductOption.productOption;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 중복 결과를 제거하면서 페치 조인(fetch joins)을 사용하여 단일 제품 엔터티를 검색합니다.
     *
     * @param expressions
     * @return 하나의 Optional 객체를 반환하는 Product entity
     */
    public Optional<Product> findDistinctOneWithFetchJoin(BooleanExpression... expressions) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(product).distinct()
                        .join(product.productOptions, productOption)
                        .where(expressions)
                        .fetchOne());
    }

    public List<Product> findAllWithFetchJoin(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(product)
                .join(product.admin, admin).fetchJoin()
                .join(product.seller, seller).fetchJoin()
                .where(expressions)
                .fetch();
    }

    public Optional<Product> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(product)
                        .where(expressions)
                        .fetchOne());
    }

    public List<Product> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(product)
                .where(expressions)
                .fetch();
    }

}
