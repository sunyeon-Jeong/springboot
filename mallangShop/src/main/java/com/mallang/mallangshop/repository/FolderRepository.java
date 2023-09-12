package com.mallang.mallangshop.repository;

import com.mallang.mallangshop.entity.Folder;
import com.mallang.mallangshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    List<Folder> findAllByUser(User user);

}