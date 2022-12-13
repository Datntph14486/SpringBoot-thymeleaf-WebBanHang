package com.example.java6_ass.service;

import com.example.java6_ass.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {
    Account create(Account account);
   public List<Account> getAll();
  public  Account getByUsername(String id);

  public List<Account> listAdmin();

}
