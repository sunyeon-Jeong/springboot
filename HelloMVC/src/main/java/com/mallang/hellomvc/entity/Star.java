package com.mallang.hellomvc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// class 모든 필드값을 파라미터로 받는 생성자추가
@AllArgsConstructor
public class Star {
    public String name;

    public int age;
}