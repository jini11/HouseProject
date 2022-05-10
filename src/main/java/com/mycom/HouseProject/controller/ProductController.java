package com.mycom.HouseProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/store")
    public String store() {
        return "product/store";
    }

    @GetMapping("/pd1")
    public String pd() {
        return "product/pd1";
    }
}
