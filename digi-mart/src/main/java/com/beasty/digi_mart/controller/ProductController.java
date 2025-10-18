package com.beasty.digi_mart.controller;

import com.beasty.digi_mart.model.Product;
import com.beasty.digi_mart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "Hello world";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return service.getAllProducts();}

    @PostMapping("/product")
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }
    @PostMapping("/products")
    public void addMultipleProducts(@RequestBody List<Product> products) {
        service.addMultipleProducts(products);
    }
    @GetMapping("/product/{id}")
    public void getProduct(@PathVariable int id){
        service.getProductById(id);
    }

}
