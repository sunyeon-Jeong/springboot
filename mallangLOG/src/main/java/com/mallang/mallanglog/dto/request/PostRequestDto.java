package com.mallang.mallanglog.dto.request;

import lombok.Getter;

// Client 요청으로 들어오는 정보를 담는 Dto
@Getter
public class PostRequestDto {

    private String title;

    private String content;

}