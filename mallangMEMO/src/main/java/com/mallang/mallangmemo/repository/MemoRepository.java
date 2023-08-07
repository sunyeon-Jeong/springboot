package com.mallang.mallangmemo.repository;

import com.mallang.mallangmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    // Memo 조회하기
    List<Memo> findAllByOrderByModifiedAtDesc();

    // 반환타입 + 조회조건
}