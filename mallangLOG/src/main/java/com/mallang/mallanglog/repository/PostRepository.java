package com.mallang.mallanglog.repository;

import com.mallang.mallanglog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // Post 전체조회 (작성시간기준 내림차순)
    List<Post> findAllByOrderByModifiedAtDesc(); // 반환타입 + 조건

    // Post 수정 (회원권한 별 인가)
    Optional<Post> findByIdAndUserId(Long id, Long userId);

}