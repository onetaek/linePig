package com.shop.linepig.domain.product.service;

import com.shop.linepig.domain.admin.entity.Admin;
import com.shop.linepig.domain.admin.repository.AdminRepository;
import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.member.repository.SellerRepository;
import com.shop.linepig.domain.product.dto.request.*;
import com.shop.linepig.domain.product.dto.response.ProductResponse;
import com.shop.linepig.domain.product.entity.Product;
import com.shop.linepig.domain.product.entity.ProductDetail;
import com.shop.linepig.domain.product.entity.ProductSpecial;
import com.shop.linepig.domain.product.entity.embeddable.UploadFile;
import com.shop.linepig.domain.product.entity.enumeration.ProductCategory;
import com.shop.linepig.domain.product.entity.enumeration.ProductSellStatus;
import com.shop.linepig.domain.product.repository.*;
import com.shop.linepig.domain.upload.UploadBase64EncodedFileRequest;
import com.shop.linepig.domain.upload.UploadFirebaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    private final ProductOptionItemRepository productOptionItemRepository;
    private final ProductSpecialRepository productSpecialRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailImageRepository productDetailImageRepository;
    private final UploadFirebaseService uploadFirebaseService;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;

    public ProductResponse create(ProductCreateRequest request, Long adminId) {

        List<UploadBase64EncodedFileRequest> productImages = request.getProductImages();//제품 이미지
        String name = request.getName();//제품이름
        String nameDescription = request.getNameDescription();//부재
        int price = request.getPrice();//가격
        String priceDescription = request.getPriceDescription();//가격설명
        int stockNumber = request.getStockNumber();
        List<ProductOptionCreateRequest> productOptions = request.getProductOptions();//옵션
        List<ProductSpecialCreateRequest> productSpecials = request.getProductSpecials();//특이사항
        List<ProductDetailCreateRequest> productDetails = request.getProductDetails();//세부정보
        List<UploadBase64EncodedFileRequest> productDetailImages = request.getProductDetailImages();//제품 상세이미지
        Long sellerId = request.getSellerId();//판매자

        //이미지 firebase에 등록
        List<UploadFile> uploadedProductImages = uploadFirebaseService.uploadFiles(productImages);
        List<UploadFile> uploadedProductDetailImages = uploadFirebaseService.uploadFiles(productDetailImages);

        Seller findSeller = sellerRepository.findById(sellerId).orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
        Admin findAdmin = adminRepository.findById(adminId).orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));


        Product product = Product.builder()
                .name(name)
                .nameDescription(nameDescription)
                .price(price)
                .priceDescription(priceDescription)
                .sequence(0)//순서 커스텀 할 수있도록 요구사항이 있으면 수정
                .stockNumber(stockNumber)
                .status(ProductSellStatus.Sell)
                .category(ProductCategory.DEFAULT)
                .admin(findAdmin)
                .seller(findSeller)
                .build();

        Product savedProduct = productRepository.save(product);

        List<ProductSpecial> toSaveProductSpecials = ProductSpecialCreateRequest.toEntities(productSpecials, savedProduct);
        productSpecialRepository.saveAll(toSaveProductSpecials);

        List<ProductDetail> toSaveProductDetails = ProductDetailCreateRequest.toEntities(productDetails, savedProduct);
        productDetailRepository.saveAll(toSaveProductDetails);



        return null;
    }
}
