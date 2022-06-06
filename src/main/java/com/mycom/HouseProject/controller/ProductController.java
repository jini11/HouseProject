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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String store(Model model, @RequestParam(required = false, defaultValue = "") String category) {
        List<Product> products;
        if(category.equals("") || category.equals("0"))
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
    @RequestMapping(value = "/register", method = RequestMethod.GET)
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
    public String manage(Model model, @PageableDefault(size = 10) Pageable pageable,
                         @RequestParam(required = false, defaultValue = "") String searchText,
                         @RequestParam(required = false) Long category) {
        Page<Product> products;
        if(category == null || category == 0)
            products = productRepository.findByNameContaining(searchText, pageable);
        else
            products = productRepository.findByNameContainingAndCategoryContaining(searchText, category, pageable);
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
    public String postRegister(@Valid Product product, BindingResult bindingResult, @RequestParam(required = false) MultipartFile imgFile,
                               @RequestParam(required = false) Long id, RedirectAttributes re) throws Exception {
        if(id != null) { // 수정
            if(bindingResult.hasFieldErrors("name") || bindingResult.hasFieldErrors("company") || bindingResult.hasFieldErrors("category")
                    || bindingResult.hasFieldErrors("price") || bindingResult.hasFieldErrors("stock")) {
                re.addAttribute(id);
                return "product/register";
            }
        } else { // 등록
            if(bindingResult.hasErrors() && imgFile.isEmpty())
                return "product/register";
        }

        if(!imgFile.isEmpty()) { // 이미지 있음
            if (id != null) { // 기존 상품 수정(이미지 수정 있음. 기존 이미지 삭제)
                System.out.println("기존 상품 수정(이미지 첨부 했음");
                productService.deleteImg(id);
            }
            productService.save(product, imgFile); // 새로운 이미지 업로드
        } else {
            System.out.println("기존 상품 수정(이미지 첨부 안했음");
            productService.save(id, product); // 기존 상품 수정(이미지 수정 없음)
        }
        return "redirect:/product/manage";
    }
}
