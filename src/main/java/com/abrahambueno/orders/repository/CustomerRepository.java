package com.abrahambueno.orders.repository;

import com.abrahambueno.orders.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByName(String name);
}
