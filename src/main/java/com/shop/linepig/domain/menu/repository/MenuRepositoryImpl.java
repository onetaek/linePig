package com.shop.linepig.domain.menu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.linepig.domain.menu.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.shop.linepig.domain.menu.entity.QMenu.menu;

@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    public List<Menu> findAllWitchOrderBy() {
        return jpaQueryFactory.selectFrom(menu)
                .orderBy(menu.sequence.asc())
                .fetch();
    }
}
