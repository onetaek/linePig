package com.shop.linepig.domain.member.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SellerCreateRequest {

    private List<SellerExtendCreateRequest> sellerExtends;

//    private String company;//상호
//    private String representative;//대표자
//    private String companyAddress;//사업장 소재지
//    private String email;//이메일
//    private String phoneNumber;//연락처
//    private String companyNumber;//사업자번호
//    private String telecommunicationNumber;//통신 판매어 신고번호

}
