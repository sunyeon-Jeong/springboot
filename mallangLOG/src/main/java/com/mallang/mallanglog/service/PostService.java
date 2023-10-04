package com.mallang.mallanglog.service;

import com.mallang.mallanglog.dto.PostRequestDto;
import com.mallang.mallanglog.dto.PostResponseDto;
import com.mallang.mallanglog.entity.Post;
import com.mallang.mallanglog.entity.User;
import com.mallang.mallanglog.jwt.JwtUtil;
import com.mallang.mallanglog.repository.PostRepository;
import com.mallang.mallanglog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/*
1. 주요 Entity class 객체를 만듦
2. Controller에서 파라미터로 가져온 RequestDto값 -> Entity 객체 덮어씌움
3. 초기화 된 Entity -> DB에 CRUD 작업
4. 초기화 된 Entity -> ResponseDto에 담아 Client에게 return
 */
@Service
@RequiredArgsConstructor // final, @Notnull 필드 -> 생성자생성
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    // Post 생성
    @Transactional // DB처리 작업 중 오류 -> 모든작업 원상태로 복구
    public PostResponseDto createPost(PostRequestDto postRequestDto,
                                      HttpServletRequest httpServletRequest) {

        // 1. HTTP Request Header -> JWT Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // 2. JWT Token 유효 -> Post 생성 가능
        if (token != null) {

            // 2-1. JWT Token 검증
            if (jwtUtil.validateToken(token)) {

                // JWT Token 에서 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 2-2. Token에서 가져온 사용자 정보 -> DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("This account does not exist")
            );

            // 2-3. 요청받은 DTO -> DB에 저장할 객체생성
            Post post = postRepository.saveAndFlush(Post.of(postRequestDto, user));

            // 2-4. ResponseDto에 객체담아 반환
            return PostResponseDto.of(post);

        } else {

            return null;

        }

    }

    // Post 전체조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts() {

        // Repository -> Entity 객체배열
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();

        // ResponseDto 배열 생성
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        // postList -> postResponseDtoList (List -> List로 바로 못 옮김)
        for (Post post : postList) {
            postResponseDtoList.add(PostResponseDto.of(post));
        }

        return postResponseDtoList;

    }

    // Post 선택조회
    @Transactional(readOnly = true)
    public PostResponseDto getSelectedPost(Long postId) {

        // Repository -> Entity 객체 담아옴 -> 예외처리
        Post selectedPost = postRepository.findById(postId).orElseThrow(
                // IllegalStateException : 적절하지못한 인자를 메서드로 넘겨주었을 때 예외
                () -> new IllegalStateException("The Post does not exist")
        );

        // Entity 객체에 담아온 것 -> ResponseDto 생성자 초기화 -> Client 반환
        return PostResponseDto.of(selectedPost);

    }

    // Post 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto,
                                      HttpServletRequest httpServletRequest) {

        // 1. HTTP Request Header -> JWT Token 가져오기
        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        // 2. JWT Token 유효 -> Post 수정 가능
        if (token != null) {

            // 2-1. JWT Token 검증
            if (jwtUtil.validateToken(token)) {

                // JWT Token 에서 사용자정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 2-2. User 유효성검사
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("This account does not exist")
            );

            // 2-3. Post 유효성검사
            Post updatePost = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("The Post does not exist")
            );

            // 2-4. 회원소유 유효성검사
            if (! updatePost.getUser().getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("You are not authorized to update this post");
            }

            // 2-5. Post 수정
            updatePost.update(postRequestDto);

            // 2-6. update 된 Post 반환
            return PostResponseDto.of(updatePost);

        } else {

            return null;

        }

    }

    // Post 삭제
    @Transactional
    public Map<Integer, String> deletePost(Long postId, PostRequestDto postRequestDto) {

        // HashMap<key : value> -> 비밀번호 유효성 검사 후, 상태메시지 반환
        Map<Integer, String> statusMessage = new HashMap<>();

        // Entity 객체 생성 -> Repository에서 id로 불러옴 -> 예외처리
        Post deletePost = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("The Post does not exist")
        );

        // 비밀번호 유효성검사 -> 상태메시지 반환
        if (postRequestDto.getPassword().equals(deletePost.getPassword())) {
            postRepository.deleteById(postId);
            statusMessage.put(200, "Deleted Post Successfully");
            return statusMessage;
        } else {
            statusMessage.put(500, "Password is incorrect");
            return statusMessage;
        }

    }

}