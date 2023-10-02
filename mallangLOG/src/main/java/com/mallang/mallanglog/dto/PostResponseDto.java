package com.mallang.mallanglog.dto;

import com.mallang.mallanglog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

// Post Entity 값(모든변경이 끝남) getter -> Dto 덮어씌운 후 -> Client에 반환
@Getter
public class PostResponseDto {

    private Long id;

    private String title;

    private String username;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    // 생성자
    @Builder
    private PostResponseDto(Long id,
                            String title, String username, String content,
                            LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // 정적팩토리메서드
    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .username(post.getUser().getUsername())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .modifiedAt(post.getModifiedAt())
                .build();
    }

}