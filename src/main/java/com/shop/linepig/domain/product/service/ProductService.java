package com.shop.linepig.domain.product.service;

import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.admin.repository.AdminRepository;
import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.member.repository.SellerRepository;
import com.shop.linepig.domain.product.dto.request.*;
import com.shop.linepig.domain.product.dto.response.ProductBasicResponse;
import com.shop.linepig.domain.product.dto.response.ProductResponse;
import com.shop.linepig.domain.product.entity.*;
import com.shop.linepig.domain.product.entity.embeddable.UploadFile;
import com.shop.linepig.domain.product.repository.*;
import com.shop.linepig.domain.product.repository.expression.ProductQueryExpression;
import com.shop.linepig.domain.upload.UploadFirebaseService;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionItemRepository productOptionItemRepository;
    private final ProductSpecialRepository productSpecialRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailImageRepository productDetailImageRepository;
    private final UploadFirebaseService uploadFirebaseService;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;
    private final ProductOptionService productOptionService;

    public ProductResponse findById(Long id) {
        Product findProduct = productQueryRepository.findDistinctOneWithFetchJoin(ProductQueryExpression.eqId(id)).orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));
        ProductResponse productResponse = ProductResponse.fromEntity(findProduct);
        return productResponse;
    }

    public List<ProductBasicResponse> findAll() {
        List<Product> findProducts = productQueryRepository.findAll();
        return findProducts
                .stream()
                .map(ProductBasicResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ProductResponse create(ProductCreateRequest productCreateRequest, Long adminId) {

        Seller findSeller = sellerRepository.findById(productCreateRequest.getSellerId()).orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));

        //제품 생성 및 저장
        Product unsavedProduct = ProductCreateRequest.toEntity(productCreateRequest, findAdmin, findSeller);
        Product savedProduct = productRepository.save(unsavedProduct);

        //특이사항 생성 및 저장
        List<ProductSpecial> unsavedProductSpecials = ProductSpecialCreateRequest.toEntities(productCreateRequest.getProductSpecials(), savedProduct);
        productSpecialRepository.saveAll(unsavedProductSpecials);

        //제품 세부정보 생성 및 저장
        List<ProductDetail> unsavedProductDetails = ProductDetailCreateRequest.toEntities(productCreateRequest.getProductDetails(), savedProduct);
        productDetailRepository.saveAll(unsavedProductDetails);

        //제품 옵션 생성 및 저장
        productCreateRequest.getProductOptions().forEach(productOption -> {
            ProductOption unsavedProductionOption = ProductOptionCreateRequest.toEntity(productOption, savedProduct);
            ProductOption savedProductOption = productOptionRepository.save(unsavedProductionOption);
            List<ProductOptionItemCreateRequest> productOptionItemCreateRequests = productOption.getProductOptionItems();
            List<ProductOptionItem> unsavedProductionOptionItems = ProductOptionItemCreateRequest.toEntities(productOptionItemCreateRequests, savedProductOption);
            productOptionItemRepository.saveAll(unsavedProductionOptionItems);
        });

        //Firebase Storage에 저장 이미지 저장
        List<UploadFile> uploadedProductImages = uploadFirebaseService.uploadFiles(productCreateRequest.getProductImages());
        List<UploadFile> uploadedProductDetailImages = uploadFirebaseService.uploadFiles(productCreateRequest.getProductDetailImages());

        //제품 이미지 생성 및 저장
        List<ProductImage> toSaveProductImages = ProductImage.createEntities(uploadedProductImages, savedProduct);
        productImageRepository.saveAll(toSaveProductImages);

        //제품 상세 이미지 생성 및 저장
        List<ProductDetailImage> toSaveProductDetailImages = ProductDetailImage.createEntities(uploadedProductDetailImages, savedProduct);
        productDetailImageRepository.saveAll(toSaveProductDetailImages);

        Product findProduct = productQueryRepository.findDistinctOneWithFetchJoin(ProductQueryExpression.eqId(savedProduct.getId()))
                .orElseThrow(() -> new IllegalArgumentException("제품을 찾을 수 없습니다."));

        System.out.println("findProduct = " + findProduct);

        return null;
    }


}
