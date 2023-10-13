package com.mallang.mallanglog.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    // build.gradle -> validation 의존성추가
    @Size(min = 4, max = 10, message = "최소 4자, 최대 10자까지 설정 가능합니다")
    @Pattern(regexp = "[a-z0-9]*$", message = "알파벳소문자 및 숫자로만 설정 가능합니다")
    private String username;

    @Size(min = 8, max = 15, message = "최소 8자, 최대 15자까지 설정 가능합니다")
    @Pattern(regexp = "[a-zA-Z0-9\\\\d`~!@#$%^&*()-_=+]*$", message = "알파벳대소문자 및 숫자로만 설정 가능합니다")
    private String password;

    // 관리자권한 T / F
    private boolean adminValidation;

    // 관리자권한 토큰
    private String adminToken;

}