package com.mallang.mallanglog.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    /* JWT Token 생성 필요값 */
    // Header key
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 사용자 권한값 key
    public static final String AUTHORIZATION_KEY = "auth";

    // JWT Token 인증방식 (Bearer + JWT Token 같이 전송)
    private static final String BEARER_PREFIX = "Bearer ";

    // JWT Token 만료시간 (1h)
    private static final long TOKEN_TIME = 60 * 60 * 1000L;

    // WT SecretKey 불러오기
    @Value("${jwt.secret.key}")
    private String secretKey;

    // JWT Token 서명 및 검증용도
    private Key key;

    // JWT Token 생성(서명) 알고리즘
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // SecretKey 디코딩 후, 서명 및 검증에 필요한 key 생성
    @PostConstruct
    public void init() {
        // secretKey -> 디코딩 -> byte배열에 담음
        byte[] bytes = Base64.getDecoder().decode(secretKey);

        // 디코딩 한 byte배열 -> JWT Token 서명 및 검증에 필요한 key 생성
        key = Keys.hmacShaKeyFor(bytes);
    }


    /* 1. Client Request Header -> JWT Token 가져오기 */
    

}