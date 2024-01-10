package com.sparta.goodssalesserver.cart.cartProduct.dto;

import com.sparta.goodssalesserver.cart.cartProduct.entity.CartProduct;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartProductResponseDto {
    private CartProduct cartProduct;

    public CartProductResponseDto(CartProduct cartProduct) {
        this.cartProduct = cartProduct;
    }
}
