package com.shop.linepig.domain.cart.service;

import com.shop.linepig.domain.cart.dto.response.CartResponse;
import com.shop.linepig.domain.cart.exception.CartNotFoundException;
import com.shop.linepig.domain.cart.repository.CartQueryRepository;
import com.shop.linepig.domain.cart.repository.expression.CartBooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    private final CartQueryRepository cartQueryRepository;

    public CartResponse findByMemberId(Long memberId) {
        return cartQueryRepository.findOneWithFetchJoin(CartBooleanExpression.eqMemberId(memberId)).orElseThrow(CartNotFoundException::new);
    }

    public CartResponse findById(Long id) {
        return cartQueryRepository.findOneWithFetchJoin(CartBooleanExpression.eqId(id)).orElseThrow(CartNotFoundException::new);
    }
}
