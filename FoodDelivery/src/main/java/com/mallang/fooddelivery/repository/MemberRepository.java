package com.mallang.fooddelivery.repository;

import com.mallang.fooddelivery.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}