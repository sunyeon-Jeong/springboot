package com.mallang.mallanglog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    // 생성자
    @Builder
    private User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // 정적팩토리메서드
    public static User of(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .build();
    }

}