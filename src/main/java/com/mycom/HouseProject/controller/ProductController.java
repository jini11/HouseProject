package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import com.mycom.HouseProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/store")
    public String store() {
        return "product/store";
    }

    @GetMapping("/pd1")
    public String pd() {
        return "product/pd1";
    }

    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("product", new Product());
        } else {
            Product product = productRepository.findById(id).orElse(null);
            model.addAttribute("product", product);
        }
        return "product/register";
    }

    @GetMapping("/manage")
    public String manage(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/manage";
    }

    @PostMapping("/register")
    public String postRegister(Product product) {
        productService.save(product);
        return "redirect:/admin/index";
    }
}
