package com.sparta.goodssalesserver.cart.repository;

import com.sparta.goodssalesserver.cart.cartProduct.entity.CartProduct;
import com.sparta.goodssalesserver.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    Optional<CartProduct> findByProduct(Product product);

    Optional<CartProduct> findByCartIdAndId(Long cartId, Long cartProductId);
}
