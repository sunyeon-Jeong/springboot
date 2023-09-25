package com.mallang.mallanglog.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StatusMessageResponseDto {

    private Integer status;

    private String message;

    // 생성자
    @Builder
    private StatusMessageResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    // 정적팩토리메서드
    public static StatusMessageResponseDto of(Integer status, String message) {
        return StatusMessageResponseDto.builder()
                .status(status)
                .message(message)
                .build();
    }

}