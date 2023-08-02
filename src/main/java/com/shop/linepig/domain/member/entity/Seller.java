package com.shop.linepig.domain.member.entity;

import com.shop.linepig.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Seller extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String company;//상호
    private String representative;//대표자
    private String companyAddress;//사업장 소재지
    private String email;//이메일
    private String phoneNumber;//연락처
    private String companyNumber;//사업자번호
    private String telecommunicationNumber;//통신 판매어 신고번호
    @OneToOne(mappedBy = "seller")
    private Member member;
}
