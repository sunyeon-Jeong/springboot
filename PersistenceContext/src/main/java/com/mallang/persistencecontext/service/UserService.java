package com.mallang.persistencecontext.service;

import com.mallang.persistencecontext.repository.UserRepository;
import com.mallang.persistencecontext.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    // Bean객체 주입
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users createUser() {
        // 테스트회원 user1 객체추가
        Users beforeSavedUser = new Users("user1", "루디", "불닭");

        // 회원 user1 객체 영속화
        Users savedUser = userRepository.save(beforeSavedUser);

        // beforeSavedUser : 영속화되기 전 상태의 일반자바객체
        // savedUser : 영속성컨텍스트 1차캐시에 저장된객체

        // 프로그램가정테스트 : 조건문 True -> 아래 구문 실행
        assert (beforeSavedUser != savedUser);

        // 1. 회원 user1 조회
        Users findUser1 = userRepository.findById("user1").orElse(null);
        assert (findUser1 == savedUser);

        // 2. 회원 user1 재조회
        Users findUser2 = userRepository.findById("user1").orElse(null);
        assert (findUser2 == savedUser);

        // 3. 회원 user1 세번째조회
        Users findUser3 = userRepository.findById("user1").orElse(null);
        assert (findUser3 == savedUser);

        return findUser3;
    }

    public Users deleteUser() {
        // 테스트회원 user1 객체추가
        Users firstUser = new Users("user1", "춘식", "망고빙수");

        // 회원 user1 객체 영속화
        Users savedFirstUser = userRepository.save(firstUser);

        // 회원 user1 삭제
        userRepository.delete(savedFirstUser);

        // 회원 user1 조회
        Users deletedUser1 = userRepository.findById("user1").orElse(null);
        assert (deletedUser1 == null);

        // 회원 user1 객체 재추가
        Users secondUser = new Users("user1", "춘식", "망고빙수");

        // 회원 user1 객체 영속화
        Users savedSecondUser = userRepository.save(secondUser);
        assert (savedFirstUser != savedSecondUser);
        assert (savedFirstUser.getUsername().equals(savedSecondUser.getUsername()));
        assert (savedFirstUser.getNickname().equals(savedSecondUser.getNickname()));
        assert (savedFirstUser.getFavoriteFood().equals(savedSecondUser.getFavoriteFood()));

        // 회원 user1 조회
        Users findUser = userRepository.findById("user1").orElse(null);
        assert (findUser == savedSecondUser);

        return findUser;
    }

    public Users updateUserFail () {
        // 테스트회원 user1 객체추가
        Users users = new Users("user1", "라이언", "매운돼지갈비찜");

        // 회원 user1 객체 영속화
        Users savedUser = userRepository.save(users);

        // 회원 nickname변경
        savedUser.setNickname("귤식이");
        // 회원 favoritefood변경
        savedUser.setFavoriteFood("삼겹살");

        // 회원 user1 조회
        Users findUser = userRepository.findById("user1").orElse(null);
        // findUser -> DB X, 1차캐시에서 가져오는 값
        assert (findUser == savedUser);
        assert (findUser.getUsername().equals(savedUser.getUsername()));
        assert (findUser.getNickname().equals(savedUser.getNickname()));
        assert (findUser.getFavoriteFood().equals(savedUser.getFavoriteFood()));

        return findUser;
    }

    public Users updateUser1() {
        // 테스트회원 user1 객체추가
        Users users = new Users("user1", "쿼카", "순대국밥");

        // 회원 user1 객체 영속화
        Users savedUser1 = userRepository.save(users);

        // 회원 nickname변경
        savedUser1.setNickname("말랑이");
        // 회원 favoritefood변경
        savedUser1.setFavoriteFood("곱도리탕");

        // user1 저장
        Users savedUser2 = userRepository.save(savedUser1);
        assert (savedUser1 == savedUser2);

        return savedUser2;
    }

    // 데이터베이스 CRUD 작업 처리 중 오류 발생 → 모든 작업을 원상태로 돌림
    @Transactional
    public Users updateUser2() {
        // 테스트회원 user1 객체추가
        Users users = new Users("user1", "콘", "소고기");

        // 회원 user1 객체 영속화
        Users savedUser = userRepository.save(users);

        // 회원 nickname변경
        savedUser.setNickname("감자");
        // 회원 favoritefood변경
        savedUser.setFavoriteFood("스테이크");

        return savedUser;
    }
}