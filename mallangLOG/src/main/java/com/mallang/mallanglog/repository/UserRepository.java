package com.mallang.mallanglog.repository;

import com.mallang.mallanglog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}