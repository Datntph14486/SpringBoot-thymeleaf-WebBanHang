package com.example.java6_ass.dao;

import com.example.java6_ass.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role,String> {
}
