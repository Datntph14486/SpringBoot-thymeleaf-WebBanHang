package com.example.java6_ass.service;

import com.example.java6_ass.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> findAll();

    Authority create(Authority authority);

    void delete(Integer id);

    public List<Authority> findAuthoritiesOfAdmin(
            );

}
