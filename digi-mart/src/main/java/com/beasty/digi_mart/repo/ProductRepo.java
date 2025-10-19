package com.beasty.digi_mart.repo;

import com.beasty.digi_mart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query("""
    SELECT p FROM Product p
    WHERE lower(p.name) LIKE lower(concat('%', :keyword, '%'))
       OR lower(p.brand) LIKE lower(concat('%', :keyword, '%'))
       OR lower(p.category) LIKE lower(concat('%', :keyword, '%'))
""")    List<Product> searchProducts(String keyword);
}
