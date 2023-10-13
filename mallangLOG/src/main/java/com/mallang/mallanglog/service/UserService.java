package com.mallang.mallanglog.service;

import com.mallang.mallanglog.dto.request.LoginRequestDto;
import com.mallang.mallanglog.dto.request.SignupRequestDto;
import com.mallang.mallanglog.dto.response.StatusMessageResponseDto;
import com.mallang.mallanglog.entity.User;
import com.mallang.mallanglog.entity.UserRoleEnum;
import com.mallang.mallanglog.jwt.JwtUtil;
import com.mallang.mallanglog.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    // adminToken
    private static final String ADMIN_TOKEN = "mallang is the best Backend Developer in the world";

    // 회원가입
    @Transactional
    public ResponseEntity<StatusMessageResponseDto> signup(SignupRequestDto signupRequestDto) {

        // id/pw 가져오기
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 회원중복확인 (Optional -> 결과값 Null 허용)
        Optional<User> duplicationTest = userRepository.findByUsername(username);

        if (duplicationTest.isPresent()) {
            throw new IllegalArgumentException("A Duplicate user already exists");
        }

        // 회원권한부여 설계
        UserRoleEnum userRoleEnum = UserRoleEnum.USER;

        if (signupRequestDto.isAdminValidation()) {

            // adminToken 유효성검사
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("This adminToken is invalid");
            }

            // adminToken 유효성검사 통과 -> role 변경
            userRoleEnum = UserRoleEnum.ADMIN;

        }

        // 회원정보 -> Entity 초기화
        User user = User.of(username, password, userRoleEnum);

        // Entity -> DB 저장
        userRepository.save(user);

        // 회원가입 성공 시, Client로 성공메시지 + 상태코드 반환
        return ResponseEntity.ok(StatusMessageResponseDto.of(200, "You have successfully signed up"));

    }

    // 로그인
    @Transactional(readOnly = true)
    public ResponseEntity<StatusMessageResponseDto> login(LoginRequestDto loginRequestDto,
                                                          HttpServletResponse httpServletResponse) {

        // id/pw 가져오기
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 회원유효성검사
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("This account does not exist")
        );

        // 비밀번호유효성검사
        if (! user.getPassword().equals(password)) {
            throw new IllegalArgumentException("This password is invalid");
        }

        // 로그인성공 -> ResponseHeader에 JWT Token 보냄
        httpServletResponse.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));

        // 로그인 성공 시, Client로 성공메시지 + 상태코드 반환
        return ResponseEntity.ok(StatusMessageResponseDto.of(200, "You have successfully logged in"));

    }

}