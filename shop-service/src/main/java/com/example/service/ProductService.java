package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateNameById(Long id, String name) {
        productRepository.updateNameById(id, name);
    }

    public void updatePriceById(Long id, Double price) {
        productRepository.updatePriceById(id, price);
    }

}