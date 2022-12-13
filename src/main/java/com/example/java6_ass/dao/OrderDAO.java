package com.example.java6_ass.dao;

import com.example.java6_ass.entity.Order;
import com.example.java6_ass.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order,Long> {
    @Query("SELECT O FROM Order O WHERE O.account.username=?1")
    List<Order> findByUsername(String username);
    @Query("SELECT O FROM Order O WHERE O.status=?1")
    List<Order> getAllOrderByStatus(Status status);
}
