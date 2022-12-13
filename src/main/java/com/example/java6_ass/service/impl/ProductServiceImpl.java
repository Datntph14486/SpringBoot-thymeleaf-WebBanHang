package com.example.java6_ass.service.impl;

import com.example.java6_ass.dao.ProductDAO;
import com.example.java6_ass.entity.Product;
import com.example.java6_ass.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> getAllProduct() {
        return productDAO.getAllProduct();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        return productDAO.findByCategoryId(cid);
    }

    @Override
    public Product create(Product product) {
        return productDAO.save(product);
    }

    @Override
    public Product update(Product product) {
        return productDAO.save(product);
    }
}
