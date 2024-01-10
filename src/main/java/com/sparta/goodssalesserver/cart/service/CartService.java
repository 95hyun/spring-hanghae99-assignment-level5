package com.sparta.goodssalesserver.cart.service;

import com.sparta.goodssalesserver.cart.cartProduct.dto.CartProductRequestDto;
import com.sparta.goodssalesserver.cart.cartProduct.dto.CartProductResponseDto;
import com.sparta.goodssalesserver.cart.cartProduct.entity.CartProduct;
import com.sparta.goodssalesserver.cart.dto.CartResponseDto;
import com.sparta.goodssalesserver.cart.entity.Cart;
import com.sparta.goodssalesserver.cart.repository.CartProductRepository;
import com.sparta.goodssalesserver.cart.repository.CartRepository;
import com.sparta.goodssalesserver.product.entity.Product;
import com.sparta.goodssalesserver.product.repository.ProductRepository;
import com.sparta.goodssalesserver.user.entity.User;
import com.sparta.goodssalesserver.user.repository.UserRepository;
import com.sparta.goodssalesserver.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;

    @Transactional
    public CartProductResponseDto addCartProduct(CartProductRequestDto cartProductRequestDto, UserDetails userDetails) {
        // CartProduct 생성
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new NoSuchElementException("사용자를 찾을 수 없습니다.")
        );
        Cart cart = cartRepository.findByUser(user);
        Product product = productRepository.findByTitle(cartProductRequestDto.getProductTitle()).orElseThrow(
                () -> new NoSuchElementException("해당 상품을 찾을 수 없습니다."));
        int selectedQuantity = cartProductRequestDto.getSelectedQuantity();

        CartProduct cartProduct = new CartProduct(product, selectedQuantity, cart);
        cart.getCartProducts().add(cartProduct);
        cartProductRepository.save(cartProduct);
        return new CartProductResponseDto(cartProduct);
    }

    @Transactional(readOnly = true)
    public CartResponseDto getCartAllProduct(UserDetailsImpl userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
        Cart cart = cartRepository.findByUser(user);
        return new CartResponseDto(cart.getCartProducts(), user);
    }

    @Transactional
    public CartProductResponseDto updateCartProduct(CartProductRequestDto requestDto) {
        Product product = productRepository.findByTitle(requestDto.getProductTitle()).orElseThrow(
                () -> new NoSuchElementException("해당 상품을 찾을 수 없습니다."));
        CartProduct cartProduct = cartProductRepository.findByProduct(product).orElseThrow(
                () -> new NoSuchElementException("장바구니 목록에 해당 상품을 찾을 수 없습니다."));
        cartProduct.updateCarProductQuantity(requestDto.getSelectedQuantity());
        return new CartProductResponseDto(cartProduct);
    }

    @Transactional
    public void deleteCartProduct(Long cartId, Long cartProductId) {
        CartProduct cartProduct = cartProductRepository.findByCartIdAndId(cartId, cartProductId).orElseThrow(
                () -> new NoSuchElementException("장바구니 목록에 해당 상품을 찾을 수 없습니다."));

        cartProductRepository.delete(cartProduct);
    }
}
