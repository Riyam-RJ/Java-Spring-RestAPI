package com.webshop.controller;


import com.webshop.entity.Customer;
import com.webshop.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public Map<String, Object> addCustomer(@RequestBody Customer customer){
        Map<String, Object> response = new HashMap<>();

        try {
            Customer persistedCustomer = customerService.addCustomer(customer);
            response.put("STATUS", "SUCCESS");
            response.put("DATA", persistedCustomer);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }

    @GetMapping("/customers")
    public Map<String, Object> getCustomers(){
        Map<String, Object> response = new HashMap<>();

        try {
            List<Customer> customers = customerService.getCustomers();
            response.put("STATUS", "SUCCESS");
            response.put("DATA", customers);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }
        return response;
    }

    @GetMapping("/customers/{id}")
    public Map<String, Object> getCustomerById(@PathVariable("id") Long customerId){
        Map<String, Object> response = new HashMap<>();

        try {
            Customer customer = customerService.getCustomerById(customerId);
            response.put("STATUS", "SUCCESS");
            response.put("DATA", customer);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }
        return response;
    }
}
