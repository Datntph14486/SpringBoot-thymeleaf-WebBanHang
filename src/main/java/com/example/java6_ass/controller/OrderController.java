package com.example.java6_ass.controller;

import com.example.java6_ass.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("checkout")
    public String checkout() {
        return "order/checkout";
    }

    @GetMapping("list")
    public String list(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders",orderService.findByUsername(username));
        return "order/list";
    }

    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order/detail";
    }

}
