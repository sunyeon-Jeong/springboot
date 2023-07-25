package com.mallang.fooddelivery.global;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// 공통 Mapping 정보가 존재할때 사용
// 부모클래스를 상속받는 자식클래스에게 맵핑정보만 제공하고 싶을 때
// Entity는 Entity만 상속받을 수 있기때문에, 클래스를 Entity에 상속시키기위한 방법
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Timestamped {
    // 생성시간
    @CreatedDate
    // Entity 수정 시, 해당 필드 수정을 막음 -> 읽기전용
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 수정시간
    @LastModifiedDate
    @Column(updatable = true) // 기본값이라 생략가능
    private LocalDateTime modifiedAt;
}