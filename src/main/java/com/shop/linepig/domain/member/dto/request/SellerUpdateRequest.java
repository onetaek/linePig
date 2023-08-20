package com.shop.linepig.domain.member.dto.request;

import com.shop.linepig.domain.member.entity.Seller;
import com.shop.linepig.domain.member.entity.SellerExtend;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SellerUpdateRequest {

    private List<SellerExtendCreateRequest> sellerExtends;
//    private String company;//상호
//    private String representative;//대표자
//    private String companyAddress;//사업장 소재지
//    private String email;//이메일
//    private String phoneNumber;//연락처
//    private String companyNumber;//사업자번호
//    private String telecommunicationNumber;//통신 판매어 신고번호

    public static List<SellerExtend> toEntities(SellerUpdateRequest request, Seller savedSeller) {
        return request.getSellerExtends().stream()
                .map(sellerExtendCreateRequest -> SellerExtend.builder()
                        .name(sellerExtendCreateRequest.getName())
                        .value(sellerExtendCreateRequest.getValue())
                        .seller(savedSeller)
                        .build()
                )
                .collect(Collectors.toList());
    }
}
