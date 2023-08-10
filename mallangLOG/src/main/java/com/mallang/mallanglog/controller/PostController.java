package com.mallang.mallanglog.controller;

import com.mallang.mallanglog.dto.PostRequestDto;
import com.mallang.mallanglog.dto.PostResponseDto;
import com.mallang.mallanglog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

}