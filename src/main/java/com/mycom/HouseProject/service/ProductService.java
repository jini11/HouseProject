package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product, MultipartFile imgFile) throws Exception{
        if(product.getDiscount() == null)
            product.setDiscount(0L);
        if(!imgFile.isEmpty()) {
            String oriImg = imgFile.getOriginalFilename();
            String imgName = "";
            String path = System.getProperty("user.dir") + "/src/main/resources/static/image/product/";
            UUID uuid = UUID.randomUUID();
            String fileName = uuid + "_" + oriImg;
            imgName = fileName;
            File saveFile = new File(path, imgName);
            imgFile.transferTo(saveFile);
            product.setImgName(imgName);
            product.setImgPath("/image/product/" + imgName);
        }
        return productRepository.save(product);
    }

    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
