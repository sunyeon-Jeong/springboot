package com.mallang.persistencecontext.repository;
import com.mallang.persistencecontext.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}