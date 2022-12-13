package com.example.java6_ass.controller;

import com.example.java6_ass.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class adminController {
    @Autowired
    ProductDAO productDAO;

    @GetMapping("admin/product")
    public String listProduct(Model model){
        System.out.println(1);
        model.addAttribute("listProduct",productDAO.findAll());
        return "admin/listProduct";
    }


}
