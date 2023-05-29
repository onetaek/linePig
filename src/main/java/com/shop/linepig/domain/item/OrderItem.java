package com.shop.linepig.domain.item;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class OrderItem {

    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    private Item item;
    private int orderPrice;
    private int count;

}
