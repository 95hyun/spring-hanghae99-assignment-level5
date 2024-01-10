package com.sparta.goodssalesserver.user.entity;

import com.sparta.goodssalesserver.cart.entity.Cart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private UserGender gender;

    private String phoneNumber;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role; // ADMIN, USER


    public User(String email, String password, UserGender gender, String phoneNumber, String address, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

}
