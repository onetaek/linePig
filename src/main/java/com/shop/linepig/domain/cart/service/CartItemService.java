package com.shop.linepig.domain.cart.service;

import com.shop.linepig.domain.cart.dto.request.CartItemCreateRequest;
import com.shop.linepig.domain.cart.repository.CartItemRepository;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.exception.MemberNotFoundException;
import com.shop.linepig.domain.member.repository.MemberRepository;
import com.shop.linepig.domain.product.entity.ProductOption;
import com.shop.linepig.domain.product.exception.ProductOptionNotFoundException;
import com.shop.linepig.domain.product.repository.ProductOptionRepository;
import com.shop.linepig.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;

    public void create(CartItemCreateRequest request, Long memberId) {
        int count = request.getCount();
        Long productOptionId = request.getProductOptionId();

        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        ProductOption findProductOption = productOptionRepository.findById(productOptionId).orElseThrow(ProductOptionNotFoundException::new);
    }
}
