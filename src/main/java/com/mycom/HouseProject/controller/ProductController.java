package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import com.mycom.HouseProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String manage(Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        int startPage = Math.max(1, products.getPageable().getPageNumber() - 4);
        int endPage = Math.min(products.getTotalPages(), products.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("products", products);
        return "product/manage";
    }

    /* 이 방법으로도 상품 삭제 가능
    @RequestMapping(value = "/manage/delete", method = RequestMethod.GET)
    public String postManageDelete(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/manage";
    }*/

    @DeleteMapping("/manage/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @PostMapping("/register")
    public String postRegister(@Valid Product product) {
        productService.save(product);
        return "redirect:/admin/index";
    }

}
