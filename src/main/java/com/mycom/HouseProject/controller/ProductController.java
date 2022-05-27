package com.mycom.HouseProject.controller;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import com.mycom.HouseProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/store")
    public String store(Model model, @RequestParam(required = false, defaultValue = "") String category) {
        List<Product> products;
        if(category.equals(""))
            products = productRepository.findAll();
        else
            products = productRepository.findByCategory(category);
        model.addAttribute("products", products);
        return "product/store";
    }

    @GetMapping("/order")
    public String order(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("product", new Product());
        } else {
            Product product = productRepository.findById(id).orElse(null);
            model.addAttribute("product", product);
        }
        return "product/order";
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
    public String postRegister(@Valid Product product, @RequestParam(required = false) MultipartFile imgFile,
                               BindingResult bindingResult, @RequestParam(required = false) Long id) throws Exception {
        if(bindingResult.hasErrors())
            return "product/manage";

        if(!imgFile.isEmpty()) {
            if (id != null) { // 기존 상품 수정(이미지 수정 있음. 기존 이미지 삭제)
                productService.deleteImg(id);
            }
            productService.save(product, imgFile); // 새로운 이미지 업로드
        } else {
            productService.save(id, product); // 기존 상품 수정(이미지 수정 없음)
        }
        return "redirect:/product/manage";
    }
}
