package com.mallang.hellomvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

// @Controller + @ResponseBody -> Json 형태의 데이터 반환
@RestController
@RequestMapping("/rest")
public class HelloRestController {
    @GetMapping("/json/string") // 아래 HTML 반환
    public String helloHtmlString() {
        return "<html><body>Hello @ResponseBody</body></html>";
    }

    @GetMapping("/json/list") // 리스트 반환
    public List<String> helloJson() {
        List<String> words = Arrays.asList("Hello", "Controller", "And", "JSON");

        return words;
    }
}