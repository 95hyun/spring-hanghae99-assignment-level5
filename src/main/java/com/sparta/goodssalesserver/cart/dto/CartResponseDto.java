package com.sparta.goodssalesserver.cart.dto;

import com.sparta.goodssalesserver.cart.cartProduct.entity.CartProduct;
import com.sparta.goodssalesserver.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CartResponseDto {
    private User user;
    private List<CartProduct> cartProducts = new ArrayList<>();

    public CartResponseDto(List<CartProduct> cartProducts, User user) {
        this.cartProducts = cartProducts;
        this.user = user;
    }
}
