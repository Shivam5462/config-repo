package com.coforge.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.finance.model.Product;
import com.coforge.finance.service.ProductService;

import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/products")
public class ProductController {
 
    @Autowired
    private ProductService productService;
 
    @PostMapping("/register")
    public ResponseEntity<Product> registerProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }
 
    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Optional<Product> product = productService.findProductByName(name);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    @GetMapping("/price-less-than/{price}")
    public ResponseEntity<List<Product>> getProductsByPriceLessThan(@PathVariable Double price) {
        List<Product> products = productService.findProductsByPriceLessThan(price);
        return ResponseEntity.ok(products);
    }
 
    @GetMapping("/price-range/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        List<Product> products = productService.findProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
 
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
 
    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}