package com.sparta.goodssalesserver.product.dto;

import com.sparta.goodssalesserver.product.entity.Product;
import com.sparta.goodssalesserver.product.entity.ProductCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;

    private String title;

    private int price;

    private int quantity;

    private ProductCategory category;

    public ProductResponseDto(Product saveProduct) {
        this.id = saveProduct.getId();
        this.title = saveProduct.getTitle();
        this.price = saveProduct.getPrice();
        this.quantity = saveProduct.getQuantity();
        this.category = saveProduct.getCategory();
    }
}
