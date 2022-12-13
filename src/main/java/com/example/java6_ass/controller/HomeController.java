package com.example.java6_ass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/admin/home/index")
    public String admin(){
        return "redirect:/admin/index.html";
    }
}
