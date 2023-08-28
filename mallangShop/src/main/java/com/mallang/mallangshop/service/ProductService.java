package com.mallang.mallangshop.service;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component // Bean 등록
public class ProductService {

    // 관심상품 등록
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws SQLException {

        Product product = Product.of(productRequestDto);

        ProductRepository productRepository = new ProductRepository();

        return productRepository.createProduct(product);

    }

    // 관심상품 조회
    public List<ProductResponseDto> getProducts() throws SQLException {

        ProductRepository productRepository = new ProductRepository();

        return productRepository.getProducts();

    }

    // 관심상품 최저가 등록
    public Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) throws SQLException {

        ProductRepository productRepository = new ProductRepository();

        Product product = productRepository.getProduct(id);

        if (product == null) {
            throw new NullPointerException("해당 상품은 존재하지 않습니다");
        }

        return productRepository.updateProduct(product.getId(), productMypriceRequestDto);

    }

}