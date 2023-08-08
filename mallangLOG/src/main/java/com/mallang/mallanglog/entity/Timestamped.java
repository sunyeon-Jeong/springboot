package com.mallang.mallanglog.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 조상클래스 (but, Table설계 X)
@Getter
@EntityListeners(AuditingEntityListener.class) // 해당 Entity 변화감지 -> 테이블데이터조작
public class Timestamped {

    // 생성시간
    @CreatedDate
    private LocalDateTime createdAt;

    // 수정시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}