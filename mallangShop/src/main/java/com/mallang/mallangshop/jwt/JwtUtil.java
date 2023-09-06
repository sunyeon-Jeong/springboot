package com.mallang.mallangshop.jwt;

import com.mallang.mallangshop.entity.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    /* 1. JWT Token 생성 필요값 */
    // Header Key 값
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 사용자 권한값 Key
    public static final String AUTHORIZATION_KEY = "auth";

    // JWT Token 인증방식 (Bearer + JWT Token 함께 전송)
    private static final String BEARER_PREFIX = "Bearer ";

    // JWT Token 만료시간
    private static final long TOKEN_TIME = 60 * 60 * 1000L; // ms기준 -> 1시간

    // application.properties -> JWT Secret Key 불러옴
    @Value("${jwt.secret.key}")
    private String secretKey;

    // JWT Token 생성 및 검증 Key 저장용도
    private Key key;

    // JWT Token 생성(사인)알고리즘
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // Spring Bean 초기화 -> 실행되는 메서드
    @PostConstruct
    public void init() {
        // secretKey -> 디코딩 -> byte배열에 담음
        byte[] bytes = Base64.getDecoder().decode(secretKey);

        // 디코딩 한 byte배열 -> JWT Token 서명 및 검증에 필요한 key 생성
        key = Keys.hmacShaKeyFor(bytes);
    }


    /* 2. Client Request Header -> JWT Token 가져오기 */
    public String getToken(HttpServletRequest httpServletRequest) {

        // Header에 있는 JWT Token -> clientToken에 담음
        String clientToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);

        // clientToken 존재 + Bearer로 시작 -> JWT Token 값만 추출
        if (StringUtils.hasText(clientToken) && clientToken.startsWith(BEARER_PREFIX)) {
            return clientToken.substring(7);
        }

        return null;

    }


    /* 3. JWT Token 생성 */
    public String createToken(String username, UserRoleEnum userRoleEnum) {

        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .claim(AUTHORIZATION_KEY, userRoleEnum)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
                        .setIssuedAt(date)
                        .signWith(key, signatureAlgorithm)
                        .compact();

    }


    /* 4. JWT Token 검증 */
    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;

        } catch (SecurityException | MalformedJwtException exception) {
            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다");
        } catch (ExpiredJwtException exception) {
            log.info("Expired JWT Token, 만료된 JWT Token 입니다");
        } catch (UnsupportedJwtException exception) {
            log.info("Unsupported JWT Token, 지원되지 않는 JWT Token 입니다");
        } catch (IllegalArgumentException exception) {
            log.info("JWT claims is empty, 잘못된 JWT Token 입니다");
        }

        return false;

    }

}