package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.FolderRequestDto;
import com.mallang.mallangshop.entity.Folder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;

    // 폴더 생성
    @PostMapping("/folders")
    public List<Folder> addFolders(@RequestBody FolderRequestDto folderRequestDto, HttpServletRequest httpServletRequest) {

        List<String> folderNames = folderRequestDto.getFolderNameList();

        return folderService.addFolders(folderNames, httpServletRequest);

    }

}