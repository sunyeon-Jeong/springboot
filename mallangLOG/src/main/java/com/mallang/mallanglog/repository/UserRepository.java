package com.mallang.mallanglog.repository;

import com.mallang.mallanglog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 회원가입 -> 중복검사
    Optional<User> findByUsername(String username);

}