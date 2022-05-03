package com.webshop.services;

import com.webshop.entity.Order;
import com.webshop.repositories.OrderRepo;
import com.webshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {

    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getOrderByCustomerId(Long id) {
        return orderRepo.findAllOrdesByCustomerId(id);
    }


}
