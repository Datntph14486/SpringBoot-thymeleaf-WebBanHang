package com.example.java6_ass.dao;

import com.example.java6_ass.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,String> {
}
