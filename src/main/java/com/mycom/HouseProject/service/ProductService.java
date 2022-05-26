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
        } else {
            String imgName = product.getImgName();
            String path = product.getImgPath();
            product.setImgName(imgName);
            product.setImgPath(path);
        }

        if(product.getDiscount() != 0) {
            Long price = product.getPrice();
            Long discount = product.getDiscount();
            int sprice = (int) (price * (1 - (discount * 0.01)));
            Long saleprice = Long.valueOf(sprice);
            product.setSaleprice(saleprice);
        } else {
            Long price = product.getPrice();
            product.setSaleprice(price);
        }
        return productRepository.save(product);
    }
    public Product save(Product product, String imgName, String imgPath) {
        if(product.getDiscount() == null)
            product.setDiscount(0L);

        if(product.getDiscount() != 0) {
            Long price = product.getPrice();
            Long discount = product.getDiscount();
            int sprice = (int) (price * (1 - (discount * 0.01)));
            Long saleprice = Long.valueOf(sprice);
            product.setSaleprice(saleprice);
        } else {
            Long price = product.getPrice();
            product.setSaleprice(price);
        }

        product.setImgName(imgName);
        product.setImgPath(imgPath);

        return productRepository.save(product);
    }
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
