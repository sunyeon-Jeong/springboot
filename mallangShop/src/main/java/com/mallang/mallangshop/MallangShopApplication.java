package com.mallang.mallangshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Timestamped 사용
public class MallangShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallangShopApplication.class, args);
    }

}