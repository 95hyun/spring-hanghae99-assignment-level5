package com.sparta.goodssalesserver.cart.cartProduct.dto;

import com.sparta.goodssalesserver.product.entity.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartProductRequestDto {

    private String productTitle;

    private int selectedQuantity;
}
