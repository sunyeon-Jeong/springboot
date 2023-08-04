package com.mallang.mallangmemo.controller;

import com.mallang.mallangmemo.dto.MemoRequestDto;
import com.mallang.mallangmemo.entity.Memo;
import com.mallang.mallangmemo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController // @Controller + @ResponseBody -> Json형태의 데이터반환
@RequiredArgsConstructor // final, @Notnull -> 생성자 자동생성
public class MemoController {
    // service단 객체 불러옴
    private final MemoService memoService;

    // 메인페이지 - html 반환
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    // Memo 생성하기
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        return memoService.createMemo(memoRequestDto);
    }
}