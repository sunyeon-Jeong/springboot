package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// JSON 형태의 데이터반환
@RestController
@RequestMapping("/api")
public class ProductController {

    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) throws SQLException {

        ProductService productService = new ProductService();

        return productService.createProduct(productRequestDto);

    }

    // 관심상품 조회
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() throws SQLException {

        ProductService productService = new ProductService();

        return productService.getProducts();

    }

    // 관심상품 최저가 등록
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto productMypriceRequestDto) throws SQLException {

        ProductService productService = new ProductService();

        return productService.updateProduct(id, productMypriceRequestDto);

    }

}