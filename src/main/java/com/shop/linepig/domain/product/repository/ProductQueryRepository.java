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

    public Optional<Product> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(product)
                        .join(product.productImages, productImage)
                        .join(product.productOptions, productOption)
                        .join(productOption.productOptionItems, productOptionItem)
                        .join(product.productSpecials, productSpecial)
                        .join(product.productDetails, productDetail)
                        .join(product.productDetailImages, productDetailImage)
                        .fetchJoin()
                        .where(expressions)
                        .fetchOne());
    }

    public List<Product> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(product)
                .join(product.productImages, productImage)
                .join(product.productOptions, productOption)
                .join(productOption.productOptionItems, productOptionItem)
                .join(product.productSpecials, productSpecial)
                .join(product.productDetails, productDetail)
                .join(product.productDetailImages, productDetailImage)
                .fetchJoin()
                .where(expressions)
                .fetch();
    }

}
