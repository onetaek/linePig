package com.shop.linepig.domain.cart.repository;

import com.shop.linepig.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
