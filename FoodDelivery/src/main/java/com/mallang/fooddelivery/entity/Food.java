package com.mallang.fooddelivery.entity;

import com.mallang.fooddelivery.entity.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foodName;

    @Column(nullable = false)
    private int price;

    // JPA 연관관계설정
    @OneToMany(mappedBy = "food", fetch = FetchType.EAGER)
    private List<Orders> orders = new ArrayList<>();

    public Food(String foodName, int price) {
        this.foodName = foodName;
        this.price = price;
    }
}