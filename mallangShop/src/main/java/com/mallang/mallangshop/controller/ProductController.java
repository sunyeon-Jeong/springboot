package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.List;

// JSON 형태의 데이터반환
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    // 객체중복생성 해결 -> 멤버변수 선언
    private final ProductService productService;

    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto, HttpServletRequest httpServletRequest) {

        return productService.createProduct(productRequestDto, httpServletRequest);

    }

    // 관심상품 조회
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts(HttpServletRequest httpServletRequest) {

        return productService.getProducts(httpServletRequest);

    }

    // 관심상품 최저가 등록
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto productMypriceRequestDto) {

        return productService.updateProduct(id, productMypriceRequestDto);

    }

}