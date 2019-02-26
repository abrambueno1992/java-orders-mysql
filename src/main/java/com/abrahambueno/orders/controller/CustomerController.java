package com.abrahambueno.orders.controller;

import com.abrahambueno.orders.model.Customer;
import com.abrahambueno.orders.model.Order;
import com.abrahambueno.orders.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
//@Api(value = "Java Orders Application", description = "An Application that implements orders with CRUD functionality")

@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {


    @Autowired
    CustomerRepository customerrepos;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerrepos.findAll();
    }
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) throws URISyntaxException {
        return customerrepos.save(customer);
    }


    @GetMapping("/customer/order")
    public List<Object[]> getCustomerWithOrders() {
        var foundCustList = customerrepos.findAllOrders();
        if (foundCustList != null) {
            return foundCustList;
        } else {
            return null;
        }
    }

    //not sure if it works
    @GetMapping("/customer/name/{custname}")
    public List<Order> getCustomerOrders(@PathVariable String custname) {
        var foundCustList = customerrepos.findByCustName(custname);
        if (foundCustList != null) {
            return foundCustList;
        } else {
            return null;
        }
    }


    @GetMapping("/customer/order/{custcode}")
    public List<Order> getOrdersByCustcode(@PathVariable Long custcode) throws URISyntaxException {
        var foundOrders = customerrepos.findOrdersByCustCode(custcode);
        if (foundOrders != null) {
            return foundOrders;
        } else {
            return null;
        }
    }

    // not sure if it works
    @DeleteMapping("/customers/custcode/{custcode}")
    public Customer deleteCustomer(@PathVariable Long custcode) throws URISyntaxException  {
        var deleteCust = customerrepos.findById(custcode);
        if (deleteCust.isPresent()) {
            customerrepos.deleteById(custcode);
//            orderrepos.deleteAllByCustCode(custcode);
            return deleteCust.get();
        } else {
            return null;
        }
    }
}
