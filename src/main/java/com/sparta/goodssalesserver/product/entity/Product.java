package com.sparta.goodssalesserver.product.entity;

import com.sparta.goodssalesserver.product.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;


    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
        this.category = requestDto.getCategory();
    }
}
