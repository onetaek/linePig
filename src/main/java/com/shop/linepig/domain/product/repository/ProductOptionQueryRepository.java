package com.shop.linepig.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.product.entity.ProductOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shop.linepig.domain.product.entity.QProductOption.productOption;
import static com.shop.linepig.domain.product.entity.QProductOptionItem.productOptionItem;

@Repository
@RequiredArgsConstructor
public class ProductOptionQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Optional<ProductOption> findDistinctOneWitchFetchJoin(BooleanExpression... expressions) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(productOption).distinct()
                        .where(expressions)
                        .join(productOption.productOptionItems, productOptionItem).fetchJoin()
                        .fetchOne());
    }

    public List<ProductOption> findDistinctAllWitchFetchJoin(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(productOption).distinct()
                        .where(expressions)
                        .join(productOption.productOptionItems, productOptionItem).fetchJoin()
                        .fetch();
    }
}
