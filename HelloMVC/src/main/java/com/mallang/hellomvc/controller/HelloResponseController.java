package com.mallang.hellomvc.controller;

import com.mallang.hellomvc.entity.Star;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response") // 전체 url ->  /response로 시작
public class HelloResponseController {
    // 방문자수 증가시키는 클래스 변수
    private static long visitCount = 0;

    // 정적웹페이지
    @GetMapping("/html/redirect")
    public String htmlFile() {
        return "redirect:/hello.html";
    }

    // 동적웹페이지
    @GetMapping("/html/templates")
    public String htmlTemplates() {
        return "hello";
    }

    // 정적웹페이지
    // 메서드 Return 값 -> View 출력 X
    // HTTP Response Body에 쓰여짐
    // json 반환
    @ResponseBody
    @GetMapping("/body/html")
    public String helloStringHTML() {
        // return값이 html -> 바로 적용되어 나옴
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>By @ResponseBody</title></head>" +
                "<body> Hello, Mallang 정적 웹 페이지!!</body>" +
                "</html>";
    }

    // 동적웹페이지
    // Thymeleaf방식
    // Model <- Client가 원하는 값, hello-visit.html을 반환
    @GetMapping("/html/dynamic")
    public String helloHtmlFile(Model model) {
        visitCount++;
        // hello-visit에 visits부분에 visitCount 삽입
        model.addAttribute("visits", visitCount);
        // Controller는 Model을 이용해 데이터를 가져옴, View에 데이터를 넘김
        return "hello-visit";
    }

    // 메서드 Return 값 -> View 출력 X
    // HTTP Response Body에 쓰여짐
    // json 반환
    @ResponseBody
    @GetMapping("/json/string")
    public String helloStringJson() {
        // 콘솔 <body></body>에 json형태로 찍힘
        return "{\"name\":\"말랑\",\"age\":20}";
    }

    @ResponseBody
    @GetMapping("/json/class")
    public Star helloJson() {
        // star 객체가 json 형태로 반환됨
        return new Star("말랑", 22);
    }
}