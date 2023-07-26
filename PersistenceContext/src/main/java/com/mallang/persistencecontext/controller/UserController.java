package com.mallang.persistencecontext.controller;

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
        User user = userService.createUser();

        userRepository.delete(user);
    }

    @GetMapping("/user/delete")
    public void deleteUser() {
        User user = userService.deleteUser();

        userRepository.delete(user);
    }

    @GetMapping("/user/update/fail")
    public void updateUserFail() {
        User user = userService.updateUserFail();

        // DB에 변경내용 적용여부 확인!!
        userRepository.delete(user);
    }

    @GetMapping("/user/update/1")
    public void updateUser1() {
        User user = userService.updateUser1();

        userRepository.delete(user);
    }

    @GetMapping("/user/update/2")
    public void updateUser2() {
        User user = userService.updateUser2();

        // DB에 변경내용 적용여부 확인!!
        userRepository.delete(user);
    }
}