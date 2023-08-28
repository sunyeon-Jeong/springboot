package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
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

    // 관심상품 조회
    @GetMapping("/products")
    public List<ProductResponseDto> getProducts() throws SQLException {

        // 반환타입 (ResponseDtoList) 객체생성
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "mallang", "");

        // DB Query 작성 및 실행
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from product");

        // DB Query 결과 -> productResponseDtoList 변환
        while (resultSet.next()) {
            Product product = new Product();

            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setImage(resultSet.getString("image"));
            product.setLink(resultSet.getString("link"));
            product.setLprice(resultSet.getInt("lprice"));
            product.setMyprice(resultSet.getInt("myprice"));

            productResponseDtoList.add(ProductResponseDto.of(product));
        }

        // DB 연결해제
        resultSet.close();
        connection.close();

        // Response 전송
        return productResponseDtoList;

    }

    // 관심상품 최저가 등록
    @PutMapping("/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto productMypriceRequestDto) throws SQLException {

        // Entity 객체생성
        Product product = new Product();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db", "mallang", "");

        // DB Query 작성
        PreparedStatement preparedStatement = connection.prepareStatement("select  * from product where id = ?");

        preparedStatement.setLong(1, id);

        // DB Query 실행
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            product.setId(resultSet.getLong("id"));
            product.setTitle(resultSet.getString("title"));
            product.setImage(resultSet.getString("image"));
            product.setLink(resultSet.getString("link"));
            product.setLprice(resultSet.getInt("lprice"));
            product.setMyprice(resultSet.getInt("myprice"));
        } else {
            throw new NullPointerException("해당 Id가 존재하지 않습니다.");
        }

        // DB Query 작성
        preparedStatement = connection.prepareStatement("update product set myprice = ? where id = ?");

        preparedStatement.setInt(1, productMypriceRequestDto.getMyprice());
        preparedStatement.setLong(2, product.getId());

        // DB Query 실행
        preparedStatement.executeUpdate();

        // DB 연결해제
        resultSet.close();
        preparedStatement.close();
        connection.close();

        // Response 전송
        return product.getId();

    }

}
