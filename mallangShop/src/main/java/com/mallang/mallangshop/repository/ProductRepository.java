package com.mallang.mallangshop.repository;

import com.mallang.mallangshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByUserId(Long userId, Pageable pageable);

    Optional<Product> findByIdAndUserId(Long id, Long userId);

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByUserIdAndFolderList_Id(Long userId, Long folerId, Pageable pageable);

}