package com.mallang.mallanglog.entity;

import com.mallang.mallanglog.dto.request.PostRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // 파라미터없는 기본생성자
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    // User Entity -> Post Entity ; 단방향연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    // Client Request 값 -> Dto에서 받아와서 -> 덮어씌움
    @Builder // 빌더패턴 : 생성자를 통해 값을 받음 (-> 정적팩토리메서드)
    private Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    // 정적팩토리메서드
    public static Post of(PostRequestDto postRequestDto, User user) {
        return Post.builder()
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .user(user)
                .build();
    }

    // Post 수정 (Service -> RequestDto -> update 메서드)
    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

}