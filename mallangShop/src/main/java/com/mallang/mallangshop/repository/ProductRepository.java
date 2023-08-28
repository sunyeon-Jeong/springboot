package com.mallang.mallangshop.repository;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductResponseDto;
import com.mallang.mallangshop.entity.Product;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component // Bean 등록
public class ProductRepository {

    // 관심상품 등록
    public ProductResponseDto createProduct(Product product) throws SQLException {

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;MODE=MYSQL", "mallang", "");

        // DB Query 작성
        PreparedStatement preparedStatement = connection.prepareStatement("select max(id) as id from product");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            product.setId(resultSet.getLong("id") + 1);
        } else {
            throw new SQLException("Product 테이블의 마지막 id 값을 찾아오지 못했습니다");
        }

        preparedStatement = connection.prepareStatement("insert into product(id, title, image, link, lprice, myprice)values(?, ?, ?, ?, ?, ?)");
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

        return ProductResponseDto.of(product);

    }

    // 관심상품 조회
    public List<ProductResponseDto> getProducts() throws SQLException {

        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;MODE=MYSQL", "mallang", "");

        // DB Query 작성 및 실행
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from product");

        // DB Query 결과 -> ProductResponseDtoList로 변환
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

        return productResponseDtoList;

    }

    // 관심상품 최저가 등록
    public Long updateProduct(Long id, ProductMypriceRequestDto productMypriceRequestDto) throws SQLException {

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;MODE=MYSQL", "mallang", "");

        // DB Query 작성
        PreparedStatement preparedStatement = connection.prepareStatement("update product set myprice = ? where id = ?");

        preparedStatement.setInt(1, productMypriceRequestDto.getMyprice());
        preparedStatement.setLong(2, id);

        // DB Query 실행
        preparedStatement.executeUpdate();

        // DB 연결해제
        preparedStatement.close();

        connection.close();

        return null;

    }

    public Product getProduct(Long id) throws SQLException {

        Product product = new Product();

        // DB 연결
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:db;MODE=MYSQL", "mallang", "");

        // DB Query 작성
        PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id = ?");

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
        }

        // DB 연결해제
        resultSet.close();

        preparedStatement.close();

        connection.close();

        return product;

    }

}