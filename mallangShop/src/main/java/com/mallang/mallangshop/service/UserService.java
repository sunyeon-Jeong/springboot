package com.mallang.mallangshop.service;

import com.mallang.mallangshop.dto.LoginRequestDto;
import com.mallang.mallangshop.dto.SignupRequestDto;
import com.mallang.mallangshop.entity.User;
import com.mallang.mallangshop.entity.UserRoleEnum;
import com.mallang.mallangshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {

        // 1. RequestDto -> ID/PW 가져옴
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        // 2. 회원중복확인
        // Optional<> -> 결과 null값 허용
        Optional<User> duplicationTest = userRepository.findByUsername(username);

        if (duplicationTest.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
        }

        // 3. RequestDto -> Email 가져옴
        String email = signupRequestDto.getEmail();

        // 4. 회원Role확인
        UserRoleEnum role  = UserRoleEnum.USER;

        if (signupRequestDto.isAdmin()) {
            // ADMIN_TOKEN 유효성검사
            if (! signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록이 불가능합니다");
            }

            // 관리자암호일치 -> Role 변경
            role = UserRoleEnum.ADMIN;
        }

        // 5. 회원정보 -> Entity 초기화(생성자)
        User user = new User(username, password, email, role);

        // 6. Entity -> DB table 저장
        userRepository.save(user);

    }

    // 로그인
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto) {

        // 1. RequestDto -> ID/PW 가져옴
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 2. 회원유효성검사
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        // 3. 비밀번호유효성검사
        if (! user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

    }

}