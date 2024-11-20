package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Product a SET a.name = :name WHERE a.id = :id")
    void updateNameById(Long id, String name);

    @Transactional
    @Modifying
    @Query("UPDATE Product a SET a.price = :price WHERE a.id = :id")
    void updatePriceById(Long id, Double price);

    @Query("SELECT a FROM Product a WHERE a.name = :name")
    Product findByName(String name);

}