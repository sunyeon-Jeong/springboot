package com.mallang.mallangmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // @EntityListeners
@SpringBootApplication
public class MallangMemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallangMemoApplication.class, args);
    }
}