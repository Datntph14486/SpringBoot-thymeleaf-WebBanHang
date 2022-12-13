package com.example.java6_ass.rest.controller;

import com.example.java6_ass.dao.OrderDAO;
import com.example.java6_ass.dao.OrderDetailDAO;
import com.example.java6_ass.entity.Order;
import com.example.java6_ass.entity.OrderDetail;
import com.example.java6_ass.entity.Status;
import com.example.java6_ass.service.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailDAO orderDetailDAO;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public Order add(@RequestBody JsonNode orderData) {
        return orderService.addOrder(orderData);
    }

    @GetMapping("/pending")
    public List<Order> getAllOrderPending() {

        return orderService.getAllOrderByStatus(Status.PENDING);
    }

    @GetMapping("/confirmed")
    public List<Order> getAllOrderConfirmed() {
        return orderService.getAllOrderByStatus(Status.CONFIRMED);
    }

    @GetMapping("/delivering")
    public List<Order> getAllOrderDelivering() {
        return orderService.getAllOrderByStatus(Status.DELIVERING);
    }

    @GetMapping("successful")
    public List<Order> getAllOrderSuccessful() {
        return orderService.getAllOrderByStatus(Status.SUCCESSFUL);
    }

    @PutMapping("update_status/{id}")
    public Order updateStatus(@PathVariable("id") Integer id, @RequestBody Order order) {
        if (order.getStatus() == Status.PENDING) {
            order.setStatus(Status.CONFIRMED);
            return orderService.updateOrder(order);
        } else if (order.getStatus() == Status.CONFIRMED) {
            order.setStatus(Status.DELIVERING);
            return orderService.updateOrder(order);
        } else if (order.getStatus() == Status.DELIVERING) {
            order.setStatus(Status.SUCCESSFUL);
            return orderService.updateOrder(order);
        }
        return order;
    }

    @GetMapping("orderdetail/{id}")
    public List<OrderDetail> getOrderDetail(@PathVariable("id") Long id) {
        return orderDetailDAO.getByIdOrder(id);
    }
}
