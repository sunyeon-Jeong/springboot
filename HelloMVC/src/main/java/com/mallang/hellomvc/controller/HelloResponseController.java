package com.mallang.hellomvc.controller;

import com.mallang.hellomvc.entity.Star;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response")
public class HelloResponseController {
    // 클래스 변수
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
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>By @ResponseBody</title></head>" +
                "<body> Hello, Mallang 정적 웹 페이지!!</body>" +
                "</html>";
    }

    // 동적웹페이지
    @GetMapping("/html/dynamic")
    public String helloHtmlFile(Model model) {
        visitCount++;
        model.addAttribute("visits", visitCount);
        return "hello-visit";
    }

    // 메서드 Return 값 -> View 출력 X
    // HTTP Response Body에 쓰여짐
    // json 반환
    @ResponseBody
    @GetMapping("/json/string")
    public String helloStringJson() {
        return "{\"name\":\"말랑\",\"age\":20}";
    }

    @ResponseBody
    @GetMapping("/json/class")
    public Star helloJson() {
        return new Star("말랑", 22);
    }
}