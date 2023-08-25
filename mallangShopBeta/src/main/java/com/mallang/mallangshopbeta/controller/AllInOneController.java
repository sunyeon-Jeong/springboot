package com.mallang.mallangshopbeta.controller;

import com.mallang.mallangshopbeta.dto.ProductRequestDto;
import com.mallang.mallangshopbeta.dto.ProductResponseDto;
import com.mallang.mallangshopbeta.entity.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

// JSON 형태의 데이터반환
@RestController
@RequestMapping("/api")
public class AllInOneController {

    // 관심상품 등록
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) throws SQLException {

        //  RequestDto -> DB에 저장할 Entity객체 생성 + 초기화
        Product product = Product.of(productRequestDto);

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "mallang", "");

        // DB Query 작성
        PreparedStatement preparedStatement = connection.prepareStatement("select max(id) as id from product");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // product id 설정 = product 테이블 마지막 Id + 1
            product.setId(resultSet.getLong("id") + 1);
        } else {
            throw new SQLException("product 테이블의 마지막 Id 값을 찾아오지 못했습니다.");
        }

        preparedStatement = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice) values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setLong(1, product.getId());
        preparedStatement.setString(2, product.getTitle());
        preparedStatement.setString(3, product.getImage());
        preparedStatement.setString(4, product.getLink());
        preparedStatement.setInt(5, product.getLprice());
        preparedStatement.setInt(6, product.getMyprice());

        // DB Query 실행
        preparedStatement.executeUpdate();

        // DB 연결해제
        preparedStatement.close();

        connection.close();

        // Response 전송
        return ProductResponseDto.of(product);

    }

}