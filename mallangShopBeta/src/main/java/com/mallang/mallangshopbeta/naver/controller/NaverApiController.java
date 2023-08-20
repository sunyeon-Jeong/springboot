package com.mallang.mallangshopbeta.naver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NaverApiController {

    private final NaverApiService naverApiService;

    // 상품검색결과 목록반환
    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String query) {
        return naverApiService.searchItems(query);
    }

}