package com.mallang.mallanglog.service;

import com.mallang.mallanglog.dto.SignupRequestDto;
import com.mallang.mallanglog.dto.StatusMessageResponseDto;
import com.mallang.mallanglog.entity.User;
import com.mallang.mallanglog.jwt.JwtUtil;
import com.mallang.mallanglog.repository.UserRepository;
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

        // 회원정보 -> Entity 초기화
        User user = User.of(username, password);

        // Entity -> DB 저장
        userRepository.save(user);

        // 회원가입 성공 시, Client로 성공메시지 + 상태코드 반환
        return ResponseEntity.ok(StatusMessageResponseDto.of(200, "You have successfully signed up"));

    }

}