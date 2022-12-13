package com.example.java6_ass.service;

import com.example.java6_ass.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void addRole(Role role);
}
