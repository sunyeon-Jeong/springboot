package com.mallang.mallangshop.entity;

import com.mallang.mallangshop.dto.ProductMypriceRequestDto;
import com.mallang.mallangshop.dto.ProductRequestDto;
import com.mallang.mallangshop.naver.dto.ItemDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Product extends Timestamped {

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

    @Column(nullable = false)
    private Long userId;

    // 생성자
    @Builder
    private Product(String title, String image, String link, int lprice, Long userId) {
        this.title = title;
        this.image = image;
        this.link = link;
        this.lprice = lprice;
        this.myprice = 0;
        this.userId = userId;
    }

    // 정적팩토리메서드
    public static Product of(ProductRequestDto productRequestDto, Long userId) {
        return Product.builder()
                .title(productRequestDto.getTitle())
                .image(productRequestDto.getImage())
                .link(productRequestDto.getLink())
                .lprice(productRequestDto.getLprice())
                .userId(userId)
                .build();
    }

    // 관심상품 최저가 등록 (update)
    public void update(ProductMypriceRequestDto productMypriceRequestDto) {
        this.myprice = productMypriceRequestDto.getMyprice();
    }

    // 관심상품 최저가 업데이트
    public void updateLprice(ItemDto itemDto) {
        this.lprice = itemDto.getLprice();
    }

}