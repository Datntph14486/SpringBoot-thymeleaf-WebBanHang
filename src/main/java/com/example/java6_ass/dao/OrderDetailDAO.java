package com.example.java6_ass.dao;

import com.example.java6_ass.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT o FROM OrderDetail o where o.order.id=?1")
    public List<OrderDetail> getByIdOrder(Long id);
}
