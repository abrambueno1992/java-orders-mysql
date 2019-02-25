package com.abrahambueno.orders.controller;

import com.abrahambueno.orders.model.Customer;
import com.abrahambueno.orders.repository.CustomerRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "Crudy SnackBar Application", description = "The classic Snack Bar Application in CRUD")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    @Autowired
    CustomerRepository custrepos;


    @ApiOperation(value = "list all Customers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/customer")
    public List<Customer> allcust()
    {
        return custrepos.findAll();
    }

    @ApiOperation(value = "Customer based off of customer id", response = Customer.class)
    @GetMapping("/customer/id/{id}")
    public Customer findCustId(@ApiParam(value = "This is the customer you seek", required = true) @PathVariable long id) {
        var foundCust = custrepos.findById(id);
        if (foundCust.isPresent()) {
            return foundCust.get();
        } else {
            return null;
        }
    }

    @GetMapping("/customer/name/{name}")
    public Customer findCustomer(@PathVariable String name)
    {
        var foundCust = custrepos.findByName(name);
        if (foundCust != null) {
            return foundCust;
        } else {
            return null;
        }
    }

    @PostMapping("/customer")
    public Customer newCustomer(@RequestBody Customer customer) throws URISyntaxException {
        return custrepos.save(customer);
    }

    @PutMapping("/customer/id/{id}")
    public Customer changeCust(@RequestBody Customer newCust, @PathVariable long id) throws URISyntaxException {
        Optional<Customer> updateCust = custrepos.findById(id);
        if (updateCust.isPresent()) {
            newCust.setId(id);
            custrepos.save(newCust);
            return newCust;
        } else {
            return null;
        }
    }

    @DeleteMapping("/customer/id/{id}")
    public Customer deleteCustomer(@PathVariable long id) {
        var foundCust = custrepos.findById(id);
        if (foundCust.isPresent()) {
            custrepos.deleteById(id);
            return foundCust.get();
        } else {
            return null;
        }
    }
}
