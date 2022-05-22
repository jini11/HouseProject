package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Product;
import com.mycom.HouseProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        if(product.getDiscount() == null)
            product.setDiscount(0L);
        return productRepository.save(product);
    }

    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
