package com.shop.linepig.domain.notice.entity;

import com.shop.linepig.domain.common.BaseEntity;
import com.shop.linepig.domain.admin.entity.Admin;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Notice extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;//제목
    private String content;//내용
    private int hit;//클릭수

    @ManyToOne
    private Admin admin;
}
