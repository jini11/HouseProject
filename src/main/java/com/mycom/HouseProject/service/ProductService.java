package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        product.setDiscount(0L);
        product.setDescription("");

        return productRepository.save(product);
    }
}
