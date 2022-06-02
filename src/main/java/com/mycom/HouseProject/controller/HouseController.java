package com.mycom.HouseProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/house")
public class HouseController {

    @GetMapping("/intro")
    public String intro() { return "house/intro";}

    @GetMapping("/visit")
    public String visit() { return "house/visit";}
}
