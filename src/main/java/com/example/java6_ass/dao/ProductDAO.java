package com.example.java6_ass.dao;

import com.example.java6_ass.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1 ")
    List<Product> findByCategoryId(String id);

    @Query("SELECT  p FROM Product p WHERE p.available=true")
    List<Product> getAllProduct();
}
