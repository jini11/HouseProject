package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Board;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.UserRepository;
import com.mycom.HouseProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "account/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "account/register";
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
        String userid = authentication.getName();
        userService.save(userid, user);
        return "redirect:/account/info";
    }

    @GetMapping("/admin_info")
    public String admin_info(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        int startPage = Math.max(1, users.getPageable().getPageNumber() - 4 );
        int endPage = Math.min(users.getTotalPages(), users.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("users", users);
        return "/account/admin_info";
    }
}
