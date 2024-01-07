package com.shop.linepig.domain.cart.service;

import com.shop.linepig.domain.cart.dto.request.CartItemUpdateRequest;
import com.shop.linepig.domain.cart.dto.request.CartItemCreateRequest;
import com.shop.linepig.domain.cart.entity.Cart;
import com.shop.linepig.domain.cart.entity.CartItem;
import com.shop.linepig.domain.cart.exception.CartItemNotFoundException;
import com.shop.linepig.domain.cart.repository.CartItemRepository;
import com.shop.linepig.domain.cart.repository.CartRepository;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.exception.MemberNotFoundException;
import com.shop.linepig.domain.member.exception.UnauthorizedAccessException;
import com.shop.linepig.domain.member.repository.MemberRepository;
import com.shop.linepig.domain.product.entity.ProductOption;
import com.shop.linepig.domain.product.exception.ProductOptionNotFoundException;
import com.shop.linepig.domain.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductOptionRepository productOptionRepository;

    public void create(CartItemCreateRequest request, Long memberId) {
        int count = request.getCount();
        Long productOptionId = request.getProductOptionId();

        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        ProductOption findProductOption = productOptionRepository.findById(productOptionId).orElseThrow(ProductOptionNotFoundException::new);

        if (findMember.getCart() == null) {//장바구니를 생성한 적이 없다면
            Cart createTargetCart = Cart.builder()
                    .member(findMember)
                    .build();
            cartRepository.save(createTargetCart);
        } else {//장바구니가 존재한다면
            Cart cart = findMember.getCart();
            CartItem createTargetCartItem = CartItem.builder()
                    .count(count)
                    .cart(cart)
                    .productOption(findProductOption)
                    .build();
            cartItemRepository.save(createTargetCartItem);
        }
    }

    public void update(Long id, Long memberId, CartItemUpdateRequest request) {
        int count = request.getCount();

        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        CartItem findCartItem = cartItemRepository.findById(id).orElseThrow(CartItemNotFoundException::new);

        Member cartMember = findCartItem.getCart().getMember();
        if (!findMember.equals(cartMember)) {//로그인한 유저랑 장바구니의 유저가 다르다면 예외
            throw new UnauthorizedAccessException();
        }
        findCartItem.update(count);
    }

    public void delete(Long id, Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        CartItem findCartItem = cartItemRepository.findById(id).orElseThrow(CartItemNotFoundException::new);

        Member cartMember = findCartItem.getCart().getMember();
        if (!findMember.equals(cartMember)) {//로그인한 유저랑 장바구니의 유저가 다르다면 예외
            throw new UnauthorizedAccessException();
        }
        cartItemRepository.delete(findCartItem);
    }
}
