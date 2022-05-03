package com.webshop.controller;

import com.webshop.entity.Item;
import com.webshop.entity.Order;
import com.webshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/items")
    public Map<String, Object> addItem(@RequestBody Item item){
        Map<String, Object> response = new HashMap<>();

        try {
            Item persistedItem = itemService.addItem(item);
            response.put("STATUS", "SUCCESS");
            response.put("DATA", persistedItem);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }

    @GetMapping("/items")
    public Map<String, Object> getItems(){
        Map<String, Object> response = new HashMap<>();

        try {
            List<Item> items = itemService.getItems();
            response.put("STATUS", "SUCCESS");
            response.put("DATA", items);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }

    @GetMapping("/items/{id}")
    public Map<String, Object> getItemById(@PathVariable("id") Long itemId){
        Map<String, Object> response = new HashMap<>();

        try {
            Item item = itemService.getItemById(itemId);
            response.put("STATUS", "SUCCESS");
            response.put("DATA", item);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }

    @PostMapping("/items/buy")
    public Map<String, Object> addItem(@RequestBody HashMap<String,Long> order){
        Map<String, Object> response = new HashMap<>();

        try {
            Order persistedItem = itemService.buy(order.get("customerId"), order.get("itemId"));
            response.put("STATUS", "SUCCESS");
            response.put("DATA", persistedItem);

        }
        catch(Exception ex) {
            response.put("STATUS", "FAILED");
            response.put("MESSAGE", ex.getMessage());
        }

        return response;
    }

}
