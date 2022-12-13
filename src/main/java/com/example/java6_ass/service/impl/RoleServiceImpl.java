package com.example.java6_ass.service.impl;

import com.example.java6_ass.dao.RoleDAO;
import com.example.java6_ass.entity.Role;
import com.example.java6_ass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;
    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleDAO.save(role);
    }
}
