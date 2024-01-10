package com.sparta.goodssalesserver.product.controller;

import com.sparta.goodssalesserver.product.dto.ProductRequestDto;
import com.sparta.goodssalesserver.product.dto.ProductResponseDto;
import com.sparta.goodssalesserver.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 상품 등록 기능 (post)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/product")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    // 선택한 상품 조회 기능 (get, {productID})
    @GetMapping("/product/{productID}")
    public ProductResponseDto getProduct(@PathVariable Long productID) {
        return productService.getProduct(productID);
    }


    // 상품 목록 조회 기능 -> 페이지 별로 조회 (get, findAll)
    @GetMapping("/product")
    public Page<ProductResponseDto> getAllProduct(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return productService.getProducts(page-1, size, sortBy, isAsc);
    }
}
