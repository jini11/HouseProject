package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Cart;
import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.CartRepository;
import com.mycom.HouseProject.repository.ProductRepository;
import com.mycom.HouseProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart save(Long id, Cart cart) { // 수정
        Cart current = cartRepository.findByid(id);
        current.setCount(cart.getCount());
        return cartRepository.save(current);
    }
}
