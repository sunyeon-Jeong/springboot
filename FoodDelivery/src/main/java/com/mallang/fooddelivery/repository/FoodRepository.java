package com.mallang.fooddelivery.repository;

import com.mallang.fooddelivery.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
// 인터페이스 생성 -> JpaRepository<Entity클래스, PK타입> 상속시킴
// 기본적인 CRUD 메서드가 자동으로 생성됨