package com.shop.linepig.domain.product.service;

import com.shop.linepig.domain.product.dto.response.ProductOptionResponse;
import com.shop.linepig.domain.product.entity.ProductOption;
import com.shop.linepig.domain.product.repository.ProductOptionQueryRepository;
import com.shop.linepig.domain.product.repository.expression.ProductOptionQueryExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionQueryRepository productOptionQueryRepository;

    public ProductOptionResponse findById(Long id) {
        ProductOption productOption = productOptionQueryRepository.findDistinctOneWitchFetchJoin(
                ProductOptionQueryExpression.eqId(id)).orElseThrow(() -> new IllegalArgumentException("옵션을 찾을 수 없습니다."));
        return ProductOptionResponse.fromEntity(productOption);
    }

    public List<ProductOptionResponse> findAll(Long[] ids) {
        List<ProductOption> productOptions = productOptionQueryRepository.findDistinctAllWitchFetchJoin(
                ProductOptionQueryExpression.inIds(ids));
        return productOptions
                .stream()
                .map(ProductOptionResponse::fromEntity)
                .collect(Collectors.toList());
    }


}
