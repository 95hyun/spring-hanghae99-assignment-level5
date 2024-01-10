package com.sparta.goodssalesserver.cart.repository;

import com.sparta.goodssalesserver.cart.entity.Cart;
import com.sparta.goodssalesserver.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}
