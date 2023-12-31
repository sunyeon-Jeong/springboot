package com.mallang.mallangshop.controller;

import com.mallang.mallangshop.dto.FolderRequestDto;
import com.mallang.mallangshop.entity.Folder;
import com.mallang.mallangshop.entity.Product;
import com.mallang.mallangshop.service.FolderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FolderController {

    private final FolderService folderService;

    // 폴더 생성
    @PostMapping("/folders")
    public List<Folder> addFolders(@RequestBody FolderRequestDto folderRequestDto, HttpServletRequest httpServletRequest) {

        List<String> folderNames = folderRequestDto.getFolderNames();

        return folderService.addFolders(folderNames, httpServletRequest);

    }

    // 폴더 전체 조회
    @GetMapping("/folders")
    public List<Folder> getFolders(HttpServletRequest httpServletRequest) {

        return folderService.getFolders(httpServletRequest);

    }

    // 폴더 별 관심상품 조회
    @GetMapping("/folders/{folderId}/products")
    public Page<Product> getProductsInFolder (@PathVariable Long folderId,
                                              @RequestParam int page,
                                              @RequestParam int size,
                                              @RequestParam String sortBy,
                                              @RequestParam boolean isAsc,
                                              HttpServletRequest httpServletRequest) {

        return folderService.getProductsInFolder(folderId, page-1, size, sortBy, isAsc, httpServletRequest);

    }

}