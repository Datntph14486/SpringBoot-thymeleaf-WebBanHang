package com.example.java6_ass.rest.controller;

import com.example.java6_ass.entity.Account;
import com.example.java6_ass.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {
    @Autowired
    AccountService accountService;
    @GetMapping
    public List<Account> getAcounts(@RequestParam("admin")Optional<Boolean> admin){
        if(admin.orElse(false)){
            return accountService.listAdmin();
        }
        return accountService.getAll();
    }
}
