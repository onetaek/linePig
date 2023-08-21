package com.shop.linepig.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shop.linepig.domain.product.entity.QProduct.product;
import static com.shop.linepig.domain.product.entity.QProductDetail.productDetail;
import static com.shop.linepig.domain.product.entity.QProductDetailImage.productDetailImage;
import static com.shop.linepig.domain.product.entity.QProductImage.productImage;
import static com.shop.linepig.domain.product.entity.QProductOption.productOption;
import static com.shop.linepig.domain.product.entity.QProductOptionItem.productOptionItem;
import static com.shop.linepig.domain.product.entity.QProductSpecial.productSpecial;

@Repository
@Slf4j
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
                jpaQueryFactory.selectFrom(product)
                        .distinct()
                        .join(product.productImages, productImage).fetchJoin()
                        .join(product.productOptions, productOption).fetchJoin()
                        .join(productOption.productOptionItems, productOptionItem).fetchJoin()
                        .join(product.productSpecials, productSpecial).fetchJoin()
                        .join(product.productDetails, productDetail).fetchJoin()
                        .join(product.productDetailImages, productDetailImage).fetchJoin()
                        .where(expressions)
                        .fetchOne());
    }

    public List<Product> findAllWithFetchJoin(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(product)
                .distinct()
                .join(product.productImages, productImage).fetchJoin()
                .join(product.productOptions, productOption).fetchJoin()
                .join(productOption.productOptionItems, productOptionItem).fetchJoin()
                .join(product.productSpecials, productSpecial).fetchJoin()
                .join(product.productDetails, productDetail).fetchJoin()
                .join(product.productDetailImages, productDetailImage).fetchJoin()
                .where(expressions)
                .fetch();
    }

}
