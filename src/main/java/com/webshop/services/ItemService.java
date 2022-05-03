package com.webshop.services;

import com.webshop.entity.Customer;
import com.webshop.entity.Item;
import com.webshop.entity.Order;
import com.webshop.repositories.CustomerRepo;
import com.webshop.repositories.ItemRepo;
import com.webshop.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ItemService {

    private final ItemRepo itemRepo;
    private final CustomerRepo customerRepo;
    private final OrderRepo orderRepo;

    public ItemService(ItemRepo itemRepo, OrderRepo orderRepo, CustomerRepo customerRepo) {
        this.itemRepo = itemRepo;
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    public Item addItem(Item item) {
        return itemRepo.saveAndFlush(item);
    }

    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepo.findById(id).orElseThrow();
    }

    public Order buy(long customerId, long itemId) {
        Order order= new Order();
        Optional<Customer> customer = customerRepo.findById(customerId);
        Optional<Item> item = itemRepo.findById(itemId);
        if(customer.isPresent() && item.isPresent()) {
            order.setItem(List.of(item.get()));
            order.setCustomer(customer.get());
            return orderRepo.saveAndFlush(order);
        }
        return null;
    }
}
