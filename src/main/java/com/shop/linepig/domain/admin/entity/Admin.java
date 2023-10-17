package com.shop.linepig.domain.admin.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE ADMIN SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Admin extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;//로그인 아이디
    private String password;//비밀번호
    private String salt;//난수
    private String name;//이름
    private String profileImgLink;
    private int auth = 10;//권한

}
