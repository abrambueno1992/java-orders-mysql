package com.abrahambueno.orders.controller;

import com.abrahambueno.orders.model.Order;
import com.abrahambueno.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
//@Api(value = "Java Orders Application", description = "An Application that implements orders with CRUD functionality")

@RestController
@RequestMapping(path  = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @Autowired
    OrderRepository orderrepos;
    @GetMapping("/orders")
    public List<Order> getAllOrders() throws URISyntaxException {
        return orderrepos.findAll();
    }
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        return orderrepos.save(order);
    }
    @GetMapping("/orders/ordnum/{ordnum}")
    public Order getOrderByOrdnum(@PathVariable Long ordnum) {
        var orderReturned = orderrepos.findById(ordnum);
        if (orderReturned.isPresent()) {
            return orderReturned.get();
        } else {
            return null;
        }
    }

    @PutMapping("/orders/ordnum/{ordnum}")
    public Order updateOrder(@RequestBody Order neworder, @PathVariable Long ordnum) throws URISyntaxException {
        Optional<Order> updateOrder = orderrepos.findById(ordnum);
        if (updateOrder.isPresent()) {
            neworder.setOrdNum(ordnum);
            orderrepos.save(neworder);
            return neworder;
        } else {
            return null;
        }
    }
}
