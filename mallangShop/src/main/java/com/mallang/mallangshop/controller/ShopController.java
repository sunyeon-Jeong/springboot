package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.entity.User;
import com.mallang.mallangshop.jwt.JwtUtil;
import com.mallang.mallangshop.repository.UserRepository;
import com.mallang.mallangshop.service.FolderService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {

    private final FolderService folderService;

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @GetMapping("/shop")
    public ModelAndView shop() {

        return new ModelAndView("index");

    }

    // 로그인 한 유저 -> 메인페이지 요청 시 폴더 반환
    @GetMapping("/user-folder")
    public String getUserInfo(Model model, HttpServletRequest httpServletRequest) {

        model.addAttribute("folders", folderService.getFolders(httpServletRequest));

        return "/index :: #fragment";

    }

    // 로그인 한 유저 -> 메인페이지 요청 시 이름반환
    @GetMapping("/user-info")
    @ResponseBody
    public String getUserName(HttpServletRequest httpServletRequest) {

        String token = jwtUtil.getToken(httpServletRequest);
        Claims claims;

        if (token != null) {

            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 사용자 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );

            return user.getUsername();

        } else {

            return "fail";

        }

    }

}