package com.mallang.mallanglog.controller;

import com.mallang.mallanglog.dto.PostRequestDto;
import com.mallang.mallanglog.dto.PostResponseDto;
import com.mallang.mallanglog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody -> JSON형태 데이터반환
@RequiredArgsConstructor // final, @Notnull 필드 -> 생성자생성
public class PostController {

    private final PostService postService;

    // Post 생성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(postRequestDto);
    }
        // 느슨한결합 : Entity 바로 반환 X, Dto에 담아 반환 !
        // @RequestBody : Client 입력값 -> HTTP Body에 JSON형태로 지정객체에 담아옴

    // Post 전체조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    // Post 선택조회
    @GetMapping("/post/{post-id}")
    public PostResponseDto getSelectedPost(@PathVariable(name = "post-id") Long postId) {
        return postService.getSelectedPost(postId);
    }

    // Post 수정
    @PutMapping("/post/{post-id}")
    public PostResponseDto updatePost(@PathVariable(name="post-id") Long postId,
                                      @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, postRequestDto);
    }

}