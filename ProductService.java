package com.coforge.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.finance.model.Product;
import com.coforge.finance.repository.ProductRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
 
@Service
@Transactional
public class ProductService {
 
    @Autowired
    private ProductRepository productRepository;
 
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
 
    public Optional<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }
 
    public List<Product> findProductsByPriceLessThan(Double price) {
        return productRepository.findByPriceLessThan(price);
    }
 
    public List<Product> findProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
 
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
 
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
 
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}