package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.service.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {

    private final FolderService folderService;

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

}