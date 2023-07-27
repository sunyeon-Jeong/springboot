package com.mallang.persistencecontext.repository;
import com.mallang.persistencecontext.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}