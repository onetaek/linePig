package com.shop.linepig.domain.admin.entity;

import com.shop.linepig.domain.common.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Admin extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;//로그인 아이디
    private String password;//비밀번호
    private String name;//이름
    private int auth = 10;//권한

}
