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

    public Cart save(String userid, Long pid, Cart cart) {
        //User user = userRepository.findByUserid(userid);
        Product product = productRepository.findByid(pid);
        cart.setUserid(userid);
        cart.setProduct(product);
        cart.setCount(cart.getCount());
        return cartRepository.save(cart);
    }
}
