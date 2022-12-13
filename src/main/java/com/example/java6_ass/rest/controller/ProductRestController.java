package com.example.java6_ass.rest.controller;

import com.example.java6_ass.entity.Product;
import com.example.java6_ass.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    ProductService productService;
    @GetMapping("list")
    List<Product> getAll() {

        return productService.findAll();
    }

    @GetMapping("{id}")
    public Product getOne(@PathVariable("id") Integer id){
        return productService.findById(id);
    }

    @PostMapping
    public Product post(@RequestBody Product product) {

        productService.create(product);
        return product;
    }

    @PutMapping("{id}")
    public Product put(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(product);
    }
    @PutMapping("del/{id}")
    public Product delêt(@PathVariable("id") Integer id, @RequestBody Product product) {
        return productService.update(product);
    }
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable("id") Integer id) {
//        productService.delete(id);
//    }
}
