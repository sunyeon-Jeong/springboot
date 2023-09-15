package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<Product> getProducts(
            @RequestParam("page") int page, // 조회할 페이지 번호 (1부터 시작)
            @RequestParam("size") int size, // 한 페이지에 보여줄 개수
            @RequestParam("sortBy") String sortBy, // 정렬항목
            @RequestParam("isAsc") boolean isAsc, // 오름차순(True), 내림차순(False)
            HttpServletRequest httpServletRequest) {

        return productService.getProducts(httpServletRequest, page-1, size, sortBy, isAsc);

    }

    // 관심상품 최저가 등록
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto productMypriceRequestDto, HttpServletRequest httpServletRequest) {

        return productService.updateProduct(id, productMypriceRequestDto, httpServletRequest);

    }

    // 관심상품에 폴더추가
    @PostMapping("products/{productId}/folder")
    public Long addFolder(@PathVariable Long productId, @RequestParam Long folderId, HttpServletRequest httpServletRequest) {

        Product product = productService.addFolder(productId, folderId, httpServletRequest);

        return product.getId();

    }

}