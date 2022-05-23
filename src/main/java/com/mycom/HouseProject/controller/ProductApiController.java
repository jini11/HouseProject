package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Board;
import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.ProductRepository;
import com.mycom.HouseProject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ProductApiController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/products")
    List<Product> all(@RequestParam(required = false, defaultValue = "") String category) {
        return repository.findAll();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct) {
        return repository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    Product one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
