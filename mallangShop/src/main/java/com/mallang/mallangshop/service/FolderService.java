package com.mallang.mallangshop.service;

import com.mallang.mallangshop.entity.Folder;
import com.mallang.mallangshop.entity.User;
import com.mallang.mallangshop.jwt.JwtUtil;
import com.mallang.mallangshop.repository.FolderRepository;
import com.mallang.mallangshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    // 폴더 생성
    @Transactional
    public List<Folder> addFolders(List<String> folderNames, HttpServletRequest httpServletRequest) {

        // Client Request -> Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // Token 유효 -> 관심상품 조회가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {

                // Token -> 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // Token에서 가져온 사용자정보 -> DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            List<Folder> folderList = new ArrayList<>();

            for (String folderName : folderNames) {

                Folder folder = Folder.of(folderName, user);
                folderList.add(folder);

            }

            return folderRepository.saveAll(folderList);

        } else {

            return null;

        }

    }

    // 폴더 조회
    public List<Folder> getFolders(HttpServletRequest httpServletRequest) {

        // Client Request -> Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // Token 유효 -> 관심상품 조회가능
        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {

                // Token -> 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // Token에서 가져온 사용자정보 -> DB조회
            User user =  userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            return folderRepository.findAllByUser(user);

        } else {

            return null;

        }

    }

}