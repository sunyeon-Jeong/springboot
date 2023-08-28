package com.mallang.mallangshop.service;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    // 객체중복생성 해결 -> 멤버변수 선언
    private final ProductRepository productRepository;

    // 관심상품 등록
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {

        Product product = productRepository.saveAndFlush(Product.of(productRequestDto));

        return ProductResponseDto.of(product);

    }

    // 관심상품 조회
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts() {

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            productResponseDtoList.add(ProductResponseDto.of(product));
        }

        return productResponseDtoList;

    }

    // 관심상품 최저가 등록
    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다;")
        );

        product.update(productMypriceRequestDto);

        return product.getId();

    }

}