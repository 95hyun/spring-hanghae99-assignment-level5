package com.sparta.goodssalesserver.product.service;

import com.sparta.goodssalesserver.product.dto.ProductRequestDto;
import com.sparta.goodssalesserver.product.dto.ProductResponseDto;
import com.sparta.goodssalesserver.product.entity.Product;
import com.sparta.goodssalesserver.product.repository.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto) {
        Product product = new Product(requestDto);
        Product saveProduct = productRepository.save(product);
        return new ProductResponseDto(saveProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow(
                () -> new NoSuchElementException("해당 상품이 등록되어 있지 않습니다.")
        );
        return new ProductResponseDto(product);

    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDto> getProducts(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC; // true면 asc
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productList = productRepository.findAll(pageable);

        return productList.map(ProductResponseDto::new);
    }
}
