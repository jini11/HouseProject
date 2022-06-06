package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Cart;
import com.mycom.HouseProject.repository.CartRepository;
import com.mycom.HouseProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/replace")
    public String replace(@Valid Cart cart, @RequestParam(required = false) Long id) {
        cartService.save(id, cart);
        return "redirect:/cart/basket";
    }
}
