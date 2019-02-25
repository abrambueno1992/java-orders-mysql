package com.abrahambueno.orders.controller;

import com.abrahambueno.orders.model.Snack;
import com.abrahambueno.orders.repository.SnackRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Crudy SnackBar Application", description = "The classic Snack Bar Application in CRUD")
@RestController
@RequestMapping(path = {}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SnackController {
    @Autowired
    SnackRepository snackrepos;

    @GetMapping("/snack")
    public List<Snack> allsnacks()
    {
        return snackrepos.findAll();
    }

    @GetMapping("/snack/vending")
    public List<Object[]> vendingSnacks()
    {
        return snackrepos.vendingSnacks();
    }

    @DeleteMapping("/snack/{id}")
    public void deleteSnack(@PathVariable Long id)
    {
        snackrepos.deleteById(id);
    }
}
