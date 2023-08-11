package com.mallang.mallanglog.service;

import com.mallang.mallanglog.dto.PostRequestDto;
import com.mallang.mallanglog.dto.PostResponseDto;
import com.mallang.mallanglog.entity.Post;
import com.mallang.mallanglog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    // Post 생성
    @Transactional // DB처리 작업 중 오류 -> 모든작업 원상태로 복구
    public PostResponseDto createPost(PostRequestDto postRequestDto) {

        // Entity 객체 생성 -> 정적팩토리메서드(생성자) 초기화
        Post post = Post.of(postRequestDto);

        // 초기화 된 Entity 객체 -> Repository 저장
        postRepository.save(post);

        // 초기화 된 Entity 객체 -> ResponseDto 생성자에 전달
        return PostResponseDto.of(post);

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
                () -> new IllegalStateException("The Post does not exist.")
        );

        // Entity 객체에 담아온 것 -> ResponseDto 생성자 초기화 -> Client 반환
        return PostResponseDto.of(selectedPost);

    }

    // Post 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {

        // Entity 객체 생성 -> Repository에서 id로 불러옴 -> 예외처리
        Post updatePost = postRepository.findById(postId).orElseThrow(
                () -> new IllegalStateException("The Post does not exist.")
        );

        // 비밀번호 유효성검사
        if (! postRequestDto.getPassword().equals(updatePost.getPassword())) {
            throw new IllegalStateException("Password is incorrect");
        }

        // RequestDto -> 불러온 Entity에 덮어씌움
        updatePost.update(postRequestDto);

        // 수정이 끝난 불러온 Entity 객체 -> ResponseDto 생성자 초기화 -> Client 반환
        return PostResponseDto.of(updatePost);

    }

}