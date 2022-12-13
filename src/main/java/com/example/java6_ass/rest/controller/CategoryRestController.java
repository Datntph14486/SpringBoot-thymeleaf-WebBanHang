package com.example.java6_ass.rest.controller;

import com.example.java6_ass.entity.Category;
import com.example.java6_ass.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("")
@RestController
@RequestMapping("/rest/category/list")
public class CategoryRestController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public List<Category> getAll(){

        return categoryService.findAll();
    }
}
