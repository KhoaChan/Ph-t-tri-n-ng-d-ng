package com.example.controller.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.controller.Models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
