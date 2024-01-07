package com.shop.linepig.domain.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
