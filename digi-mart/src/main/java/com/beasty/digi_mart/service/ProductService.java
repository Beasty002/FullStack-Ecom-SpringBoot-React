package com.beasty.digi_mart.service;


import com.beasty.digi_mart.model.Product;
import com.beasty.digi_mart.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts(){
    return repo.findAll();
    }

    public void addProduct(Product prod) {
        repo.save(prod);
    }
    public void addMultipleProducts(List<Product> products) {
        repo.saveAll(products);
    }

    public void getProductById(int id) {
        repo.findById(id);
    }
}
