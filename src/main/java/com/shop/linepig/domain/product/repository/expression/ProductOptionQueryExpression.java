package com.shop.linepig.domain.product.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.shop.linepig.domain.product.entity.QProductOption;

public class ProductOptionQueryExpression {

    public static BooleanExpression eqId(Long id) {
        return id != null ? QProductOption.productOption.id.eq(id) : null;
    }

    public static BooleanExpression inIds(Long[] ids) {
        return ids != null && ids.length > 0 ? QProductOption.productOption.id.in(ids) : null;
    }
}
