package com.example.controller.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controller.Models.CartItem;
import com.example.controller.Models.Order;
import com.example.controller.Models.OrderDetail;
import com.example.controller.Repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(String customerName, List<CartItem> items) {
        Order order = new Order();
        order.setCustomerName(customerName);

        double total = 0.0;
        for (CartItem ci : items) {
            OrderDetail od = new OrderDetail();
            od.setBookId(ci.getBookId());
            od.setTitle(ci.getTitle());
            od.setPrice(ci.getPrice());
            od.setQuantity(ci.getQuantity());
            od.setOrder(order);
            order.getDetails().add(od);
            total += ci.getPrice() * ci.getQuantity();
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }
}
