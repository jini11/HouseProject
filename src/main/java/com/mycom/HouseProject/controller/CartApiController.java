package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Cart;
import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.CartRepository;
import com.mycom.HouseProject.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
class CartApiController {

    @Autowired
    private CartRepository repository;

    @Autowired
    private ProductRepository productRepository;

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

    @DeleteMapping("/deletes/{userid}")
    void deleteCartUser(@PathVariable String userid) {
        repository.deleteByUserid(userid);
    }

    @PutMapping("/addCart")
    Cart addCart(@Valid Cart cart, Authentication authentication, @RequestParam("pid") Long pid, @RequestParam("count") Long count) throws IOException {
        String userid = authentication.getName();
        Product product = productRepository.findByid(pid);
        cart.setUserid(userid);
        cart.setProduct(product);
        cart.setCount(count);
        return repository.save(cart);
    }
}