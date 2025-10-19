package com.beasty.digi_mart.controller;

import com.beasty.digi_mart.model.Product;
import com.beasty.digi_mart.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet() {
        return "Hello world";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
//        return ResponseEntity.ok(service.getAllProducts());

    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        Product prod = service.getProductById(id).orElse(null);
        if (prod != null) {
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product prod, @RequestPart MultipartFile imageFile) {
        try {
            Product product = service.addProduct(prod, imageFile);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/product/{prodId}")
    public ResponseEntity<?> updateProduct(@PathVariable int prodId,@RequestPart Product prod, @RequestPart MultipartFile imageFile) {
        try {
            Product product = service.updateProduct(prodId, prod, imageFile);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = service.getProductById(productId).orElse(null);
        assert product != null;
        byte[] imageData = product.getImageData();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageData);
    }


    @PostMapping("/products")
    public void addMultipleProducts(@RequestBody List<Product> products) {
        service.addMultipleProducts(products);
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId){
        Optional<Product> prod = service.getProductById(prodId);
        if (prod.isPresent()) {
            service.removeProduct(prodId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Not found Product",HttpStatus.NOT_FOUND);

    }
@GetMapping("/products/search")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam String keyword) {
        List<Product> products = service.searchProduct(keyword);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
