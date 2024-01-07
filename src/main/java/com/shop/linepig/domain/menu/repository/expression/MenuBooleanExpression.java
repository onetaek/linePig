package com.shop.linepig.domain.menu.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.shop.linepig.domain.menu.entity.QMenu.menu;

public class MenuBooleanExpression {
    public static BooleanExpression parentMenuIsNull() {
        return menu.parentMenu.isNull();
    }
}
