package com.webshop.controller;

import com.webshop.entity.Order;
import com.webshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Map<String, Object> getCustomers(){
        Map<String, Object> response = new HashMap<>();

        try {
            List<Order> orders = orderService.getOrders();
            response.put("STATUS", "SUCCESS");
            response.put("DATA", orders);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }

    @GetMapping("/orders/{customerId}")
    public Map<String, Object> getOrdersByCustomer(@PathVariable("customerId") Long customerId){
        Map<String, Object> response = new HashMap<>();

        try {
            List<Order> orders = orderService.getOrderByCustomerId(customerId);
            response.put("STATUS", "SUCCESS");
            response.put("DATA", orders);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }
}
