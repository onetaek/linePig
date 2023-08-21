package com.shop.linepig.domain.product.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.shop.linepig.domain.product.entity.QProduct;


public class ProductQueryExpression {

    public static BooleanExpression eqId(Long id) {
        return id != null ? QProduct.product.id.eq(id) : null;
    }

}
