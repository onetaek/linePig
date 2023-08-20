package com.shop.linepig.domain.product.repository;

import com.shop.linepig.domain.product.entity.ProductOptionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionItemRepository extends JpaRepository<ProductOptionItem, Long> {
}
