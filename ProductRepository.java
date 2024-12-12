package com.coforge.finance.repository;

import com.coforge.finance.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import java.util.List;
import java.util.Optional;
 
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
 
    Optional<Product> findByName(String name);
 
    List<Product> findByPriceLessThan(Double price);
 
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
 
    List<Product> findAll();
 
}