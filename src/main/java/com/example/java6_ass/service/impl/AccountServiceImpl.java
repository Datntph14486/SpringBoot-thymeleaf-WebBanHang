package com.example.java6_ass.service.impl;

import com.example.java6_ass.dao.AccountDAO;
import com.example.java6_ass.entity.Account;
import com.example.java6_ass.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account create(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public List<Account> getAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account getByUsername(String id) {
        return accountDAO.findById(id).get();
    }

    @Override
    public List<Account> listAdmin() {
        return accountDAO.getAdmin();
    }
}
