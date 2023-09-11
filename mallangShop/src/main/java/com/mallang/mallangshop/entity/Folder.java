package com.mallang.mallangshop.entity;

import com.mallang.mallangshop.dto.FolderRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    // 생성자
    @Builder
    private Folder(String name, User user) {
        this.name = name;
        this.user = user;
    }

    // 정적팩토리메서드
    public static Folder of(String name, User user) {
        return Folder.builder()
                .name(name)
                .user(user)
                .build();
    }

}