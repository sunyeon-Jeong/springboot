package com.mallang.mallangshopbeta.naver.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ItemDto {

    private String title;

    private String link;

    private String image;

    private int lprice;

    // 생성자
    @Builder
    private ItemDto(String title, String link, String image, int lprice) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
    }

    // 정적팩토리메서드
    public static ItemDto of(JSONObject itemJson) {
        return ItemDto.builder()
                .title(itemJson.getString("title"))
                .link(itemJson.getString("link"))
                .image(itemJson.getString("image"))
                .lprice(itemJson.getInt("lprice"))
                .build();
    }

}