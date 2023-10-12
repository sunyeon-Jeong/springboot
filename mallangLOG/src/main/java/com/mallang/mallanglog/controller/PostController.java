package com.mallang.mallanglog.controller;

import com.mallang.mallanglog.dto.request.PostRequestDto;
import com.mallang.mallanglog.dto.response.PostResponseDto;
import com.mallang.mallanglog.dto.response.StatusMessageResponseDto;
import com.mallang.mallanglog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody -> JSON형태 데이터반환
@RequiredArgsConstructor // final, @Notnull 필드 -> 생성자생성
public class PostController {

    private final PostService postService;

    // Post 생성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto,
                                      HttpServletRequest httpServletRequest) {

        return postService.createPost(postRequestDto, httpServletRequest);

    }

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
                                      @RequestBody PostRequestDto postRequestDto,
                                      HttpServletRequest httpServletRequest) {

        return postService.updatePost(postId, postRequestDto, httpServletRequest);

    }

    // Post 삭제
    @DeleteMapping("/post/{post-id}")
    public ResponseEntity<StatusMessageResponseDto> deletePost(@PathVariable(name="post-id") Long postId,
                                                               HttpServletRequest httpServletRequest) {

        return postService.deletePost(postId, httpServletRequest);

    }

}