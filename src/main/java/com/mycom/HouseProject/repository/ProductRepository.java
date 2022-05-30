package com.mycom.HouseProject.repository;

import com.mycom.HouseProject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByCategory(String category);

    Product findByid(Long id);
    Page<Product> findByNameContainingAndCategoryContaining(String name, Long category, Pageable pageable);
    Page<Product> findByNameContaining(String name, Pageable pageable);
}
