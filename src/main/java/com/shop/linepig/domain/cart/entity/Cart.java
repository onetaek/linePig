package com.shop.linepig.domain.cart.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import com.shop.linepig.domain.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE CART SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Cart extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Member member;//회원
}
