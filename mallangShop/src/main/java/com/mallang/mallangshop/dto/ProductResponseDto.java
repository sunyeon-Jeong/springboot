package com.mallang.mallangshop.dto;

import com.mallang.mallangshop.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private Long id;

    private String title;

    private String link;

    private String image;

    private int lprice;

    private int myprice;

    // 생성자
    @Builder
    private ProductResponseDto(Long id, String title, String link, String image, int lprice, int myprice) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
        this.myprice = myprice;
    }

    // 정적팩토리메서드
    public static ProductResponseDto of(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .link(product.getLink())
                .image(product.getImage())
                .lprice(product.getLprice())
                .myprice(product.getMyprice())
                .build();
    }

}