package com.sparta.goodssalesserver.cart.controller;

import com.sparta.goodssalesserver.cart.cartProduct.dto.CartProductRequestDto;
import com.sparta.goodssalesserver.cart.cartProduct.dto.CartProductResponseDto;
import com.sparta.goodssalesserver.cart.dto.CartResponseDto;
import com.sparta.goodssalesserver.cart.entity.Cart;
import com.sparta.goodssalesserver.cart.service.CartService;
import com.sparta.goodssalesserver.user.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 장바구니 추가 기능 (선택한 상품을 장바구니에 추가) (post)
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/cart")
    public CartProductResponseDto addCartProduct(
            @RequestBody CartProductRequestDto cartProductRequestDto,
            @AuthenticationPrincipal UserDetails userDetails) {
        return cartService.addCartProduct(cartProductRequestDto, userDetails);
    }

    // 장바구니 조회 (get)
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/cart")
    public CartResponseDto getCartAllProduct(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cartService.getCartAllProduct(userDetails);
    }

    // 장바구니 수정 (장바구니에서 선택한 상품의 수량을 수정가능) (put)
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart")
    public CartProductResponseDto updateCartProduct(@RequestBody CartProductRequestDto requestDto) {
        return cartService.updateCartProduct(requestDto);
    }

    // 장바구니 삭제 (장바구니에서 선택한 상품을 삭제 가능) (delete)
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/cart/{cartId}/cart-product/{cartProductId}")
    public ResponseEntity<String> deleteCartProduct(@PathVariable Long cartId, @PathVariable Long cartProductId) {
        cartService.deleteCartProduct(cartId, cartProductId);
        return ResponseEntity.ok("장바구니 목록의 해당 상품을 성공적으로 삭제하였습니다.");
    }
}
