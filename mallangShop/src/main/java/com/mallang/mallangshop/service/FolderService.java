package com.mallang.mallangshop.service;

import com.mallang.mallangshop.entity.Folder;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.entity.User;
import com.mallang.mallangshop.jwt.JwtUtil;
import com.mallang.mallangshop.repository.FolderRepository;
import com.mallang.mallangshop.repository.ProductRepository;
import com.mallang.mallangshop.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    private final ProductRepository productRepository;

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

            // 폴더명 중복검사
            List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

            List<Folder> folderList = new ArrayList<>();

            for (String folderName : folderNames) {

                if (! isExistFolderName(folderName, existFolderList)) {

                    Folder folder = new Folder(folderName, user);
                    folderList.add(folder);

                }

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

    // 폴더 별 관심상품 조회
    @Transactional(readOnly = true)
    public Page<Product> getProductsInFolder(Long folderId, int page, int size, String sortBy, boolean isAsc, HttpServletRequest httpServletRequest) {

        // 페이징처리
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        // JWT Token
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // JWT Token 유효성검사
        if (token != null) {

            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 사용자 유효성검사
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            System.out.println("FolderService.getProductsInFolder");
            System.out.println("folderId = " + folderId);
            System.out.println("user = " + user.getId());

            return productRepository.findAllByUserIdAndFolderList_Id(user.getId(), folderId, pageable);

        } else {

            return null;

        }

    }

    // 폴더명 중복검사
    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {

        for (Folder existFolder : existFolderList) {

            if (existFolder.getName().equals(folderName)) {
                return true;
            }

        }

        return false;

    }

}