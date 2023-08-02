package com.mallang.mallangmemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController // @Controller + @ResponseBody -> Json형태의 데이터반환
@RequiredArgsConstructor // final, @Notnull -> 생성자 자동생성
public class MemoController {
    // html 반환
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
}