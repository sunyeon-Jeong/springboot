package com.mallang.mallanglog.entity;

import com.mallang.mallanglog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor // final, @Notnull 필드 -> 생성자생성
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String content;

    // Client Request 값 -> Dto에서 받아와서 -> 덮어씌움
    @Builder // 빌더패턴 : 생성자를 통해 값을 받음 (-> 정적팩토리메서드)
    private Post(String title, String username, String password, String content) {
        this.title = title;
        this.username = username;
        this.password = password;
        this.content = content;
    }

    // 정적팩토리메서드
    public static Post of(PostRequestDto postRequestDto) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .username(postRequestDto.getUsername())
                .password(postRequestDto.getPassword())
                .content(postRequestDto.getContent())
                .build();
    }

}