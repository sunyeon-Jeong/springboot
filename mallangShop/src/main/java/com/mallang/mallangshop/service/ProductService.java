package com.mallang.mallangshop.service;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.entity.User;
import com.mallang.mallangshop.entity.UserRoleEnum;
import com.mallang.mallangshop.jwt.JwtUtil;
import com.mallang.mallangshop.naver.dto.ItemDto;
import com.mallang.mallangshop.repository.ProductRepository;
import com.mallang.mallangshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    // 관심상품 등록
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto, HttpServletRequest httpServletRequest) {

        // 1. HTTP Request Header -> JWT Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // 2. JWT Token 있는경우에만 -> 관심상품 등록 가능
        if (token != null) {

            // 2-1. JWT Token 검증
            if (jwtUtil.validateToken(token)) {

                // true -> Token에서 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 2-2. Token에서 가져온 사용자정보 -> DB조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            // 2-3. 요청받은 DTO -> DB에 저장할 객체생성
            Product product = productRepository.saveAndFlush(Product.of(productRequestDto, user.getId()));

            return ProductResponseDto.of(product);

        } else {

            return null;

        }

    }

    // 관심상품 조회
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProducts(HttpServletRequest httpServletRequest) {

        // 1. HTTP Request Header -> JWT Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // 2. JWT Token 있는경우에만 -> 관심상품 조회 가능
        if (token != null) {

            // 2-1. JWT Token 검증
            if (jwtUtil.validateToken(token)) {

                // true -> Token에서 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 2-2. Token에서 가져온 사용자정보 -> DB조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            // 2-3. 사용자권한 ADMIN ; 전체조회, USER ; 본인 관심상품조회
            UserRoleEnum userRoleEnum = user.getRole();
            System.out.println("role = " + userRoleEnum);

            List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
            List<Product> productList;

            if (userRoleEnum == UserRoleEnum.USER) {
                productList = productRepository.findAllByUserId(user.getId());
            } else {
                productList = productRepository.findAll();
            }

            for (Product product : productList) {
                productResponseDtoList.add(ProductResponseDto.of(product));
            }

            return productResponseDtoList;

        } else {

            return null;

        }

    }

    // 관심상품 최저가 등록
    @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto, HttpServletRequest httpServletRequest) {

        // 1. HTTP Request Header -> JWT Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // 2. JWT Token 있는경우에만 -> 관심상품 등록 가능
        if (token != null) {

            // 2-1. JWT Token 검증
            if (jwtUtil.validateToken(token)) {

                // true -> Token에서 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 2-2. Token에서 가져온 사용자정보 -> DB조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            Product product = productRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new IllegalArgumentException("해당상품은 존재하지 않습니다")
            );

            product.update(productMypriceRequestDto);

            return product.getId();

        } else {

            return null;

        }
    }

    // 관심상품 최저가 업데이트
    public void updateBySearch(Long id, ItemDto itemDto) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 상품은 존재하지 않습니다")
        );

        // Product Entity method 실행
        product.updateLprice(itemDto);

    }

}