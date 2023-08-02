package com.shop.linepig.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.linepig.domain.member.entity.Seller;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class SellerResponse {

    private Long id;
    private String company;//상호
    private String representative;//대표자
    private String companyAddress;//사업장 소재지
    private String email;//이메일
    private String phoneNumber;//연락처
    private String companyNumber;//사업자번호
    private String telecommunicationNumber;//통신 판매어 신고번호

    public static SellerResponse fromEntity(Seller seller) {
        if(seller == null)
            return null;
        return SellerResponse.builder()
                .id(seller.getId())
                .company(seller.getCompany())
                .representative(seller.getRepresentative())
                .companyAddress(seller.getCompanyAddress())
                .email(seller.getEmail())
                .phoneNumber(seller.getPhoneNumber())
                .companyNumber(seller.getCompanyNumber())
                .telecommunicationNumber(seller.getTelecommunicationNumber())
                .build();
    }
}
