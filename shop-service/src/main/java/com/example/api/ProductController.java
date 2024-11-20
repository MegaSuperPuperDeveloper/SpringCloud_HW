package com.example.api;

import com.example.model.Product;
import com.example.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        if (productService.getAllProducts() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (productService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        if (productService.getProductByName(name) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }

    @PostMapping("/{name}/{price}")
    public ResponseEntity<Void> createProduct(@PathVariable String name, @PathVariable Double price) {
        if (name == null || price < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        productService.addProduct(new Product(name, price));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/name/{name}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @PathVariable String name) {
        if (productService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.updateNameById(id, name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/price/{price}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @PathVariable Double price) {
        if (productService.getProductById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.updatePriceById(id, price);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}