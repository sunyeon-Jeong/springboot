package com.mallang.persistencecontext.controller;

import com.mallang.persistencecontext.repository.UserRepository;
import com.mallang.persistencecontext.service.UserService;
import com.mallang.persistencecontext.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
// View 대신 Json 형태 데이터 -> ResponseBody에 반환 (데이터만 반환하면되는 컨트롤러)
// RESTful API
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    // Bean객체 주입
    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/create")
    public void createUser() {
        Users users = userService.createUser();

        userRepository.delete(users);
    }

    @GetMapping("/user/delete")
    public void deleteUser() {
        Users users = userService.deleteUser();

        userRepository.delete(users);
    }

    @GetMapping("/user/update/fail")
    public void updateUserFail() {
        Users users = userService.updateUserFail();

        // DB에 변경내용 적용여부 확인!!
        userRepository.delete(users);
    }

    @GetMapping("/user/update/1")
    public void updateUser1() {
        Users users = userService.updateUser1();

        userRepository.delete(users);
    }

    @GetMapping("/user/update/2")
    public void updateUser2() {
        Users users = userService.updateUser2();

        // DB에 변경내용 적용여부 확인!!
        userRepository.delete(users);
    }
}