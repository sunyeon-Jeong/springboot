package com.mallang.fooddelivery.repository;

import com.mallang.fooddelivery.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}