package com.example.java6_ass.controller;

import com.example.java6_ass.dao.CategoryDAO;
import com.example.java6_ass.dao.ProductDAO;
import com.example.java6_ass.entity.Product;
import com.example.java6_ass.service.CategoryService;
import com.example.java6_ass.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    CategoryService categoryService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("list")
    public String list(Model model, @RequestParam("p") Optional<Integer> p) {
        if (p.isPresent()) {
            org.springframework.data.domain.Pageable pageable = PageRequest.of(p.orElse(0) - 1, 6);
            Page<Product> page = productDAO.findAll(pageable);
            model.addAttribute("listProduct", page);
            int pageSize = page.getSize();
            int totalPage = page.getTotalPages();
            int currentPage = page.getNumber();
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("currentPage", currentPage);
            return "product/list";
        } else {
            org.springframework.data.domain.Pageable pageable = PageRequest.of(p.orElse(0), 6);
            Page<Product> page = productDAO.findAll(pageable);
            int pageSize = page.getSize();
            int totalPage = page.getTotalPages();
            int currentPage = page.getNumber();
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("listProduct", page);
            return "product/list";
        }
    }

    @GetMapping("detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }


    @GetMapping("")
    public String getByCategory(Model model, @RequestParam("cid") Optional<String> cid) {
        if (cid.isPresent()) {
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("listProduct", list);
            return "product/list";
        } else {
            List<Product> list = productService.findAll();
            model.addAttribute("listProduct", list);
            return "product/list";

        }
    }

}
