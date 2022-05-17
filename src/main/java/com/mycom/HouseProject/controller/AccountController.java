package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Board;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.UserRepository;
import com.mycom.HouseProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session) {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/info")
    public String info(Authentication authentication, Model model) {
        String userid = authentication.getName();
        User users = userRepository.findByUserid(userid);
        model.addAttribute("user", users);
        return "account/info";
    }

    @PostMapping("/info")
    public String postInfo(@Valid User user, BindingResult bindingResult, Authentication authentication) {
        if(bindingResult.hasErrors())
            return "account/info";
        userService.save(user);
        return "redirect:/account/info";
    }
}
