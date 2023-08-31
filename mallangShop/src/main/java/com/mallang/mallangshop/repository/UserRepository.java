package com.mallang.mallangshop.repository;

import com.mallang.mallangshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}