package com.shop.linepig.domain.product.repository;

import com.shop.linepig.domain.product.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
