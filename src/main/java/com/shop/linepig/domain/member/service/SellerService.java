package com.shop.linepig.domain.member.service;

import com.shop.linepig.domain.member.dto.request.SellerCreateRequest;
import com.shop.linepig.domain.member.dto.request.SellerExtendCreateRequest;
import com.shop.linepig.domain.member.dto.request.SellerExtendUpdateRequest;
import com.shop.linepig.domain.member.dto.request.SellerUpdateRequest;
import com.shop.linepig.domain.member.dto.response.SellerResponse;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.member.entity.SellerExtend;
import com.shop.linepig.domain.member.entity.enumeration.MemberStatus;
import com.shop.linepig.domain.member.repository.MemberRepository;
import com.shop.linepig.domain.member.repository.SellerExtendRepository;
import com.shop.linepig.domain.member.repository.SellerQueryRepository;
import com.shop.linepig.domain.member.repository.SellerRepository;
import com.shop.linepig.domain.member.repository.expression.SellerQueryExpression;
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
    private final SellerQueryRepository sellerQueryRepository;
    private final SellerExtendRepository sellerExtendRepository;

    public List<SellerResponse> findAll() {
        return sellerQueryRepository.findAllWithFetchJoin()
                .stream()
                .map(SellerResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public SellerResponse findById(Long id) {
        return SellerResponse.fromEntity(
                sellerQueryRepository.findOneWithFetchJoin(SellerQueryExpression.eqId(id))
                .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다.")));
    }

    public SellerResponse create(Long id, SellerCreateRequest request) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 회원을 찾을 수 없습니다."));
        findMember.setStatus(MemberStatus.SELLER);

        Seller seller = Seller.builder()
                .member(findMember)
                .build();
        Seller savedSeller = sellerRepository.save(seller);
        List<SellerExtend> savedSellerExtends = sellerExtendRepository.saveAll(SellerExtendCreateRequest.toEntities(request, savedSeller));
        return SellerResponse.fromEntity(savedSeller.setSellerExtends(savedSellerExtends));
    }

    public SellerResponse update(Long id, SellerUpdateRequest request) {
        Seller findSeller = sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 판매자가 없습니다."));
        List<SellerExtend> sellerExtends = SellerExtendUpdateRequest.toEntities(request, findSeller);
        Seller update = findSeller.update(sellerExtends);
        return SellerResponse.fromEntity(update);
    }

    public void delete(Long id) {
        Seller findSeller = sellerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 판매자가 없습니다."));
        sellerRepository.delete(findSeller);
    }
}
