package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Cart;
import com.mycom.HouseProject.repository.CartRepository;
import com.mycom.HouseProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @GetMapping("/basket")
    public String basket(Model model, Authentication authentication) {
        String userid = authentication.getName();
        List<Cart> carts = cartRepository.findByUserid(userid);
        model.addAttribute("carts", carts);
        return "cart/basket";
    }

    @PostMapping("/addCart/{pid}/{count}")
    public String addCart(@Valid Cart cart, Authentication authentication, @PathVariable Long pid, @PathVariable Long count) throws IOException {
        String userid = authentication.getName();
        cartService.save(userid, pid, cart);
        return "redirect:/product/order";
    }
}
