package com.shop.linepig.domain.member.service;

import com.shop.linepig.domain.member.dto.request.SellerCreateRequest;
import com.shop.linepig.domain.member.dto.request.SellerExtendCreateRequest;
import com.shop.linepig.domain.member.dto.request.SellerUpdateRequest;
import com.shop.linepig.domain.member.dto.response.SellerResponse;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.member.entity.SellerExtend;
import com.shop.linepig.domain.member.repository.MemberRepository;
import com.shop.linepig.domain.member.repository.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class SellerService {

    private final MemberRepository memberRepository;
    private final SellerRepository sellerRepository;

    public SellerResponse create(Long id, SellerCreateRequest request) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 회원을 찾을 수 없습니다."));
        Seller seller = Seller.builder()
                .member(findMember)
                .build();
        Seller savedSeller = sellerRepository.save(seller);
        List<SellerExtend> sellerExtends = this.getSellerExtends(request, savedSeller);
        sellerExtends.forEach(seller::addSellerExtend);
        return SellerResponse.fromEntity(savedSeller);
    }

    public SellerResponse update(Long id, SellerUpdateRequest request) {
        Seller findSeller = sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 판매자가 없습니다."));
        List<SellerExtend> sellerExtends = this.getSellerExtends(request, findSeller);
        Seller update = findSeller.update(sellerExtends);
        return SellerResponse.fromEntity(update);
    }

    public void delete(Long id) {
        Seller findSeller = sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 판매자가 없습니다."));
        sellerRepository.delete(findSeller);
    }


    private List<SellerExtend> getSellerExtends(SellerCreateRequest request, Seller savedSeller) {
        List<SellerExtendCreateRequest> sellerExtends = request.getSellerExtends();
        List<SellerExtend> extendsToSave = sellerExtends.stream()
                .map(sellerExtendCreateRequest -> SellerExtend.builder()
                        .name(sellerExtendCreateRequest.getName())
                        .value(sellerExtendCreateRequest.getValue())
                        .seller(savedSeller)
                        .build()
                )
                .collect(Collectors.toList());
        return extendsToSave;
    }

    private List<SellerExtend> getSellerExtends(SellerUpdateRequest request, Seller savedSeller) {
        List<SellerExtendCreateRequest> sellerExtends = request.getSellerExtends();
        List<SellerExtend> extendsToSave = sellerExtends.stream()
                .map(sellerExtendCreateRequest -> SellerExtend.builder()
                        .name(sellerExtendCreateRequest.getName())
                        .value(sellerExtendCreateRequest.getValue())
                        .seller(savedSeller)
                        .build()
                )
                .collect(Collectors.toList());
        return extendsToSave;
    }
}
