package com.example.java6_ass.controller;

import com.example.java6_ass.entity.Account;
import com.example.java6_ass.entity.AccountBean;
import com.example.java6_ass.entity.Authority;
import com.example.java6_ass.entity.Role;
import com.example.java6_ass.service.AccountService;
import com.example.java6_ass.service.AuthorityService;
import com.example.java6_ass.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class SecurityController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    AccountService accountService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    RoleService roleService;

    @GetMapping("security/loginForm")
    public String login(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "security/login";
    }

    @GetMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công!");
        return "redirect:/product/list";

    }

    @GetMapping("/security/login/error")
    public String logout(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "security/login";
    }

    @GetMapping("/security/logout/success")
    public String logoutSuccess(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "redirect:/security/loginForm";

    }

    @GetMapping("/security/register")
    public String register(Model model) {
        model.addAttribute("account", new AccountBean());
        request.setAttribute("error", "");
        return "security/register";
    }

    @PostMapping("/security/register")
    public String register(@ModelAttribute("account") AccountBean account) {
//        Account accountCheck = accountService.getByUsername(account.getUsername());
//        System.out.println(accountCheck);
//        System.out.println(1);
        List<Account> list = accountService.getAll();
        for (Account a: list) {
            if(a.getUsername().equals(account.getUsername())) {
                request.setAttribute("error", " Tài khoản đã tồn tại!");
                return "security/register";
            }
        }
        System.out.println(account);
        account.setPhoto("user.png");
        if (!account.getPassword().equals(account.getRepeatpassword())
                || account.getPassword().trim().equals("") || account.getEmail().trim().equals("") || account.getFullname().trim().equals("") ) {
            request.setAttribute("error", "Lỗi Đăng ký tài khoản!");
            return "security/register";
        }
        Account account1 = new Account();
        account1.setEmail(account.getEmail());
        account1.setFullname(account.getFullname());
        account1.setPassword(account.getPassword());
        account1.setUsername(account.getUsername());
        account1.setPhoto("user.png");

        accountService.create(account1);
        Role role = new Role();
        role.setId("CUST");
        role.setName("Customers");
        roleService.addRole(role);
        Authority authority = new Authority();
        authority.setAccount(account1);
        authority.setRole(role);
        authorityService.create(authority);
        request.setAttribute("error", "Đăng ký tài khoản thành công!");
        return "redirect:/security/loginForm";
    }
}
