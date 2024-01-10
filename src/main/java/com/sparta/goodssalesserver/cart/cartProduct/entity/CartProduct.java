package com.sparta.goodssalesserver.cart.cartProduct.entity;

import com.sparta.goodssalesserver.cart.cartProduct.dto.CartProductRequestDto;
import com.sparta.goodssalesserver.cart.entity.Cart;
import com.sparta.goodssalesserver.product.entity.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int selectedQuantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartProduct(Product product, int selectedQuantity, Cart cart) {
        this.product = product;
        this.selectedQuantity = selectedQuantity;
        this.cart = cart;
    }

    public void updateCarProductQuantity(int selectedQuantity) {
        this.selectedQuantity = selectedQuantity;
    }
}
