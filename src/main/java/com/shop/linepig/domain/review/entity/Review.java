package com.shop.linepig.domain.review.entity;

import com.shop.linepig.domain.common.mappedsuperclass.BaseEntity;
import com.shop.linepig.domain.member.entity.Member;
import com.shop.linepig.domain.product.entity.Product;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Getter
@Entity
@SQLDelete(sql = "UPDATE REVIEW SET deleted = 1, deleted_on = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
public class Review extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;//회원
    @ManyToOne
    private Product product;//제품
}
