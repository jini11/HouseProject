package com.mycom.HouseProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myshop")
public class MyShopController {

    @GetMapping("/index")
    public String index() {
        return "myshop/index";
    }

    @GetMapping("/info")
    public String info() {
        return "myshop/info";
    }

    @GetMapping("/wish")
    public String wish() {
        return "myshop/wish";
    }
}
