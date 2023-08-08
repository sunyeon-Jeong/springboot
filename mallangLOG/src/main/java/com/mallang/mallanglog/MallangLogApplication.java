package com.mallang.mallanglog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @EntityListeners와 연결
public class MallangLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallangLogApplication.class, args);
    }

}