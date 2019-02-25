package com.abrahambueno.orders.controller;

import com.abrahambueno.orders.model.VendingMachine;
import com.abrahambueno.orders.repository.VendingMachineRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "Crudy SnackBar Application", description = "The classic Snack Bar Application in CRUD")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class VendingMachineController {
    @Autowired
    VendingMachineRepository vendingrepos;

    @GetMapping("/vending")
    public List<VendingMachine> allvending()
    {
        return vendingrepos.findAll();
    }

    @GetMapping("/vending/id/{id}")
    public List<VendingMachine> getVendId(@PathVariable long id) {
        return vendingrepos.findById(id).stream().collect(Collectors.toList());
    }

    @GetMapping("/vending/{name}")
    public List<VendingMachine> namedvending(@PathVariable String name)
    {
        return vendingrepos.findByName(name);
    }

    @PostMapping("/vending")
    public VendingMachine newVending(@RequestBody VendingMachine vendingMachine) throws URISyntaxException {
        return vendingrepos.save(vendingMachine);
    }

    @PutMapping("/vending/id/{id}")
    public List<VendingMachine> changeVending(@RequestBody VendingMachine newVending, @PathVariable long id) throws URISyntaxException {
        Optional<VendingMachine> updatedVending = vendingrepos.findById(id);
        if (updatedVending.isPresent()) {
            newVending.setId(id);
            vendingrepos.save(newVending);
            return java.util.Arrays.asList(newVending);
        } else {
            return updatedVending.stream().collect(Collectors.toList());
        }
    }

    @DeleteMapping("/vending/{id}")
    public List<VendingMachine> deleteVendingMachine(@PathVariable long id)
    {
        List<VendingMachine> rmvending = vendingrepos.findById(id).stream().collect(Collectors.toList());
        vendingrepos.deleteById(id);
        return rmvending;
    }
}
