package com.mallang.mallanglog.service;

import com.mallang.mallanglog.dto.PostRequestDto;
import com.mallang.mallanglog.dto.PostResponseDto;
import com.mallang.mallanglog.entity.Post;
import com.mallang.mallanglog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}