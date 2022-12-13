package com.example.java6_ass.service;

import com.example.java6_ass.entity.Order;
import com.example.java6_ass.entity.Status;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OrderService {
    public Order addOrder(JsonNode order);

    public void deleteOrder(Order order);

    public Order updateOrder(Order order);

    public Order getOrderById(Long id);

    public Order getOrderByOrderId(int id);

    public List<Order> findByUsername(String username);

    public List<Order> getAllOrderByStatus(Status status);

    List<Order> findAll();
}
