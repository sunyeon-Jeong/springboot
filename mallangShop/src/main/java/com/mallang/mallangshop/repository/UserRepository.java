package com.mallang.mallangshop.repository;

import com.mallang.mallangshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 회원중복확인
    Optional<User> findByUsername(String username);

}