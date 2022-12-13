package com.example.java6_ass.service.impl;

import com.example.java6_ass.dao.AccountDAO;
import com.example.java6_ass.dao.AuthorityDAO;
import com.example.java6_ass.entity.Account;
import com.example.java6_ass.entity.Authority;
import com.example.java6_ass.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    AuthorityDAO authorityDAO;

    @Autowired
    AccountDAO accountDAO;
    @Override
    public List<Authority> findAll() {
        return authorityDAO.findAll();
    }

    @Override
    public Authority create(Authority authority) {
        return authorityDAO.save(authority);
    }

    @Override
    public void delete(Integer id) {
        authorityDAO.deleteById(id);

    }

    @Override
    public List<Authority> findAuthoritiesOfAdmin() {
        List<Account> accounts = accountDAO.getAdmin();
        return authorityDAO.authoritiesOf(accounts);
    }

}
