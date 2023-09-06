package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.LoginRequestDto;
import com.mallang.mallangshop.dto.SignupRequestDto;
import com.mallang.mallangshop.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private  final UserService userService;

    // 회원가입 페이지반환
    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    // 로그인 페이지반환
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto) {

        userService.signup(signupRequestDto);

        return "redirect:/api/user/login";

    }

    // 로그인
    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {

        userService.login(loginRequestDto, httpServletResponse);

        return "success";

    }

}