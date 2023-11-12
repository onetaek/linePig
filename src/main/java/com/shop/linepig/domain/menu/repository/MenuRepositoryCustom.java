package com.shop.linepig.domain.menu.repository;

import com.shop.linepig.domain.menu.entity.Menu;

import java.util.List;

public interface MenuRepositoryCustom {
    List<Menu> findAllWitchOrderBy();
}
