package com.sparta.goodssalesserver.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.goodssalesserver.cart.cartProduct.entity.CartProduct;
import com.sparta.goodssalesserver.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartProduct> cartProducts = new ArrayList<>();


    public Cart(User user) {
        this.user = user;
    }
}
