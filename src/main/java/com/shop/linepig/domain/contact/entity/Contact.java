package com.shop.linepig.domain.contact.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import com.shop.linepig.domain.admin.entity.Admin;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE CONTACT SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Contact extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;//제목
    private String content;//내용

    @ManyToOne
    private Admin admin;//관리자
}
