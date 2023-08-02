package com.mallang.mallangmemo.repository;

import com.mallang.mallangmemo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}