package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Cart;
import com.mycom.HouseProject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /*@RequestMapping(value = "/cart/addCart/{pid}", method = RequestMethod.POST)
    public String addCart(@Valid Cart cart, @PathVariable Long pid, BindingResult bindingResult, Authentication authentication) {
        System.out.println("idid:::::::::::::::: " + pid);
        System.out.println("countounc: ::::::::::: " + cart.getCount());
        String userid = authentication.getName();
        if(userid != null) {
            cartService.save(userid, pid, cart);
        }
        return "redirect:/";
    }*/

    @PostMapping("/addCart/{pid}/{count}")
    public String addCart(@Valid Cart cart, Authentication authentication, @PathVariable Long pid,
                          @PathVariable Long count) throws IOException {

        String userid = authentication.getName();
        cartService.save(userid, pid, cart);
        return "redirect:/product/order";
    }
}
