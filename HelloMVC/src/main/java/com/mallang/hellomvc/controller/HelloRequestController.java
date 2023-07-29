package com.mallang.hellomvc.controller;

import com.mallang.hellomvc.entity.Star;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request") // request path로 시작
public class HelloRequestController {
    // hello-request-form html파일 호출
    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    @GetMapping("/star/{name}/age/{age}")
    // View가 아니라, Json형태의 데이터만 반환
    // @Controller 사용했기때문에 메서드마다 붙여줘야함
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age) {
        // @PathVariable : URL로 전달된 값 -> 파라미터로 받아옴
        return String.format("Hello, @PathVariable. <br> name = %s, age = %d", name, age);
    }

    // param?name=mallang&age=22 (쿼리스트링)
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
        // @RequestParam : URL로 전달된 값 -> 파라미터로 받아옴
        // Get방식은 URL에 값이 표출됨
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", name, age);
    }

    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        // Post방식은 URL에 값이 표출되지 않음
        // 페이로드에 값이 들어있음
        return String.format("Hello, @RequestParam. <br> name = %s, age = %d", name, age);
    }

    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) {
        // @ModelAttribute : 한번에 데이터를 객체에 전부 담아 가져옴
        return String.format("Hello, @RequestBody. <br> (name = %s, age = %d)", star.name, star.age);
    }

    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        // 값이 HTTP Body에 Json형태로 넘어감
        // @RequestBody + 값을 받아올 객체
        return String.format("Hello, @RequestBody. <br> (name = %s, age = %d)", star.name, star.age);
    }
}