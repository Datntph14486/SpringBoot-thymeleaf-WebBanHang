package com.example.java6_ass.service;

import com.example.java6_ass.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    List<Product> getAllProduct();

    Product findById(int id);

    Product save(Product product);

    List<Product> findByCategoryId(String cid);

    Product create(Product product);

    Product update(Product product);
}
