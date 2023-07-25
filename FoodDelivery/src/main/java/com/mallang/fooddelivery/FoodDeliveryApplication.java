package com.mallang.fooddelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FoodDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);
    }

}
