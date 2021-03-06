package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product, MultipartFile imgFile) throws Exception{ //저장
        // discount
        if(product.getDiscount() == null)
            product.setDiscount(0L);

        // 이미지 업로드
        String oriImg = imgFile.getOriginalFilename();
        String imgName = "";
        String path = System.getProperty("user.home") + "/tomcat/webapps/upload/";
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + oriImg;
        imgName = fileName;
        File saveFile = new File(path, imgName); // 새로운 이미지 업로드
        imgFile.transferTo(saveFile);
        product.setImgName(imgName);
        product.setImgPath("/upload/" + imgName);

        // saleprice
        if(product.getDiscount() != 0) {
            Long price = Long.valueOf(product.getPrice());
            Long discount = product.getDiscount();
            int sprice = (int) (price * (1 - (discount * 0.01)));
            Long saleprice = Long.valueOf(sprice);
            product.setSaleprice(saleprice);
        } else {
            Long price = Long.valueOf(product.getPrice());
            product.setSaleprice(price);
        }
        return productRepository.save(product);
    }

    public Product save(Long id, Product product) { // 수정
        Product current = productRepository.findByid(id);

        current.setName(product.getName());
        current.setCompany(product.getCompany());
        current.setCategory(product.getCategory());
        current.setPrice(product.getPrice());
        current.setStock(product.getStock());
        current.setDescription(product.getDescription());

        // discount
        if(product.getDiscount() == null || product.getDiscount() == 0)
            current.setDiscount(0L);
        else current.setDiscount(product.getDiscount());

        // saleprice
        if(current.getDiscount() != 0) {
            Long price = Long.valueOf(current.getPrice());
            Long discount = current.getDiscount();
            int sprice = (int) (price * (1 - (discount * 0.01)));
            Long saleprice = Long.valueOf(sprice);
            current.setSaleprice(saleprice);
        } else {
            Long price = Long.valueOf(current.getPrice());
            current.setSaleprice(price);
        }

        return productRepository.save(current);
    }

    public void deleteImg(@PathVariable Long id) { // 기존 이미지 삭제
        Product current = productRepository.findByid(id);

        String path = System.getProperty("user.home") + "/tomcat/webapps/upload/";
        File lastFile = new File(path, current.getImgName()); // 기존 이미지
        if(lastFile.exists()) // 기존 이미지가 있다면 삭제
            lastFile.delete();
    }

    public void deleteProduct(@PathVariable Long id) { // 상품 삭제
        productRepository.deleteById(id);
    }
}
