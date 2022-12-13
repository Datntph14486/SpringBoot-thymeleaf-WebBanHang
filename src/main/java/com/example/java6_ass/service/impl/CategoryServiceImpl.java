package com.example.java6_ass.service.impl;

import com.example.java6_ass.dao.CategoryDAO;
import com.example.java6_ass.entity.Category;
import com.example.java6_ass.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
}
