package com.sparta.goodssalesserver.user.service;

import com.sparta.goodssalesserver.cart.entity.Cart;
import com.sparta.goodssalesserver.cart.repository.CartRepository;
import com.sparta.goodssalesserver.user.dto.SignupRequestDto;
import com.sparta.goodssalesserver.user.dto.SignupResponseDto;
import com.sparta.goodssalesserver.user.entity.User;
import com.sparta.goodssalesserver.user.entity.UserGender;
import com.sparta.goodssalesserver.user.entity.UserRoleEnum;
import com.sparta.goodssalesserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    @Transactional
    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String email = requestDto.getEmail();
        // 이메일 중복 확인
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email이 존재합니다.");
        }

        String password = passwordEncoder.encode(requestDto.getPassword());
        UserGender gender = requestDto.getGender();
        String phoneNumber = requestDto.getPhoneNumber();
        String address = requestDto.getAddress();

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(email, password, gender, phoneNumber, address, role);

        // 개인 장바구니 생성
        cartRepository.save(new Cart(user));

        // db 저장
        User saveUser = userRepository.save(user);

        return new SignupResponseDto(saveUser);
    }
}
