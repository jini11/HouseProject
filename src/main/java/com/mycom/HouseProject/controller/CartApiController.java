package com.mycom.HouseProject.controller;


import com.mycom.HouseProject.model.Board;
import com.mycom.HouseProject.model.Cart;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
class CartApiController {

    @Autowired
    private CartRepository repository;

    @GetMapping("/carts")
    List<Cart> all() {
        return repository.findAll();
    }

    @PostMapping("/carts")
    Cart newCart(@RequestBody Cart newCart) {
        return repository.save(newCart);
    }

    @GetMapping("/carts/{id}")
    Cart one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/carts/{id}")
    void deleteCart(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/carts/{id}/{count}")
    Cart replaceCart(@PathVariable Long id, @PathVariable Long count) {
        Cart cart = repository.findByid(id);
        cart.setCount(count);
        return repository.save(cart);
    }
}