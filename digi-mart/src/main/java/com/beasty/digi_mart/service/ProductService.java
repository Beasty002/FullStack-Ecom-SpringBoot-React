package com.beasty.digi_mart.service;


import com.beasty.digi_mart.model.Product;
import com.beasty.digi_mart.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;
    public List<Product> getAllProducts(){
    return repo.findAll();
    }

    public Product addProduct(Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        return repo.save(prod);

    }
    public void addMultipleProducts(List<Product> products) {
        repo.saveAll(products);
    }

    public Optional<Product> getProductById(int id) {
        return repo.findById(id);
    }

    public Product updateProduct(int prodId, Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getOriginalFilename());
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        return repo.save(prod);}

    public void removeProduct(int prodId) {
        repo.deleteById(prodId);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProducts(keyword);
    }
}
