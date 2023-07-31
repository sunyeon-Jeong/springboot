package com.mallang.mallangmemo.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 조상클래스
// @EntityListeners -> Application에 @EnableJpaAuditing 추가필수
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {
    // 생성시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}