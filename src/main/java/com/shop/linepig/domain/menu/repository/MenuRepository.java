package com.shop.linepig.domain.menu.repository;

import com.shop.linepig.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>, MenuRepositoryCustom{
}
