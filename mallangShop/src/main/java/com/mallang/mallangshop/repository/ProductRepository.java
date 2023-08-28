package com.mallang.mallangshop.repository;

import com.mallang.mallangshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}