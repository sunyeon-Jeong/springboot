package com.mallang.mallangshop.entity;

import com.mallang.mallangshop.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id 자동생성 및 증가
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    // 생성자
    @Builder
    private Product(String title, String image, String link, int lprice) {
        this.title = title;
        this.image = image;
        this.link = link;
        this.lprice = lprice;
        this.myprice = 0;
    }

    // 정적팩토리메서드
    public static Product of(ProductRequestDto productRequestDto) {
        return Product.builder()
                .title(productRequestDto.getTitle())
                .image(productRequestDto.getImage())
                .link(productRequestDto.getLink())
                .lprice(productRequestDto.getLprice())
                .build();
    }

}