package com.example.java6_ass.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    OrderDetailDAO orderDetailDAO;



    @Override
    public Order addOrder(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Order order =mapper.convertValue(orderData,Order.class);

        orderDAO.save(order);

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {

        };

        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"),type).stream().peek(d-> d.setOrder(order)).collect(Collectors.toList());
        orderDetailDAO.saveAll(details);

        return order;
    }

    @Override
    public void deleteOrder(Order order) {

    }

    @Override
    public Order updateOrder(Order order) {
  return   orderDAO.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDAO.findById(id).get();
    }

    @Override
    public Order getOrderByOrderId(int id) {
        return null;
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderDAO.findByUsername(username);
    }

    @Override
    public List<Order> getAllOrderByStatus(Status status) {
        return orderDAO.getAllOrderByStatus(status);
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
}
