package com.mallang.mallangshopbeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 파라미터없는 기본생성자
@AllArgsConstructor // 모든 필드를 파라미터로받는 생성자
public class ProductRequestDto {

    private String title;

    private String image;

    private String link;

    private int lprice;

}