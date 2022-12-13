package com.example.java6_ass.dao;

import com.example.java6_ass.entity.Account;
import com.example.java6_ass.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityDAO extends JpaRepository<Authority,Integer> {
    @Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
    List<Authority> authoritiesOf(List<Account> accounts);
}
