package com.shop.linepig.domain.admin.repository.expression;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.shop.linepig.domain.admin.entity.QAdmin.admin;

public class AdminQueryExpression {

    public static BooleanExpression eqLoginId(String loginId) {
        if (loginId == null) return null;
        return admin.loginId.eq(loginId);
    }

    public static BooleanExpression eqPassword(String password) {
        if (password == null) return null;
        return admin.password.eq(password);
    }
}
