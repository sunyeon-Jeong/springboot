package com.mallang.mallanglog.controller;

import com.mallang.mallanglog.dto.request.LoginRequestDto;
import com.mallang.mallanglog.dto.request.SignupRequestDto;
import com.mallang.mallanglog.dto.response.StatusMessageResponseDto;
import com.mallang.mallanglog.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<StatusMessageResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {

        return userService.signup(signupRequestDto);

    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<StatusMessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                                          HttpServletResponse httpServletResponse) {

        return userService.login(loginRequestDto, httpServletResponse);

    }

}