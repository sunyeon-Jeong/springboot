package com.mallang.mallangshop.config;

import com.mallang.mallangshop.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 직접 객체를 사용 -> Bean DI 주입
@Configuration
public class BeanConfiguration {

    @Bean
    public ProductRepository productRepository() {

        String dbUrl = "jdbc:h2:mem:db";
        String username = "mallang";
        String password = "";

        return new ProductRepository(dbUrl, username, password);

    }

}