package com.shop.linepig.domain.cart.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.cart.dto.response.CartItemResponse;
import com.shop.linepig.domain.cart.dto.response.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.shop.linepig.domain.cart.entity.QCart.cart;
import static com.shop.linepig.domain.cart.entity.QCartItem.cartItem;
import static com.shop.linepig.domain.member.entity.QMember.member;
import static com.shop.linepig.domain.product.entity.QProduct.product;
import static com.shop.linepig.domain.product.entity.QProductOption.productOption;

@Repository
@RequiredArgsConstructor
public class CartQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Optional<CartResponse> findOneWithFetchJoin(BooleanExpression... expressions) {
        return Optional.ofNullable(jpaQueryFactory.select(Projections.fields(CartResponse.class,
                        cart.id.as("id"),
                        Projections.fields(CartItemResponse.class,
                                cartItem.id.as("cartItemId"),
                                cartItem.count,
                                member.id.as("memberId"),
                                member.loginId.as("memberLoginId"),
                                member.name.as("memberName"),
                                product.nameKo.as("productNameKo"),
                                product.nameEn.as("productNameEn"),
                                product.nameDescriptionKo.as("productNameDescriptionKo"),
                                product.nameDescriptionEn.as("productNameDescriptionEn"),
                                product.optionKo.as("productOptionKo"),
                                product.optionEn.as("productOptionEn"),
                                product.representativePriceKo.as("productRepresentativePriceKo"),
                                product.representativePriceKo.as("productRepresentativePriceEn"),
                                product.representativeImage.as("productRepresentativeImage"),
                                productOption.valueKo.as("productOptionValueKo"),
                                productOption.valueEn.as("productOptionValueEn"),
                                productOption.priceKo.as("productOptionPriceKo"),
                                productOption.priceEn.as("productOptionPriceEn"),
                                productOption.stockQuantity.as("stockQuantity"),
                                productOption.sequence.as("sequence"))))
                .from(cart)
                .where(expressions)
                .fetchOne());
    }

}
