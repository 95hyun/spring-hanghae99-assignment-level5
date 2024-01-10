package com.sparta.goodssalesserver.product.dto;

import com.sparta.goodssalesserver.product.entity.ProductCategory;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRequestDto {
    private String title;

    private int price;

    private int quantity;

    private ProductCategory category;
}
