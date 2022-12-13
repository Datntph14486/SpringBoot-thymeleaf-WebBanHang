package com.example.java6_ass.dao;

import com.example.java6_ass.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountDAO extends JpaRepository<Account, String> {
    @Query("SELECT DISTINCT ar.account  FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
    List<Account> getAdmin();
}
