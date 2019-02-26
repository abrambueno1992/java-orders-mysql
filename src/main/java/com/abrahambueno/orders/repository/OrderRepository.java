package com.abrahambueno.orders.repository;

import com.abrahambueno.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // something wrong
    @Query(value = "delete from orders where oders.custcode = :custcode", nativeQuery = true)
    void deleteAllByCustCode(Long custcode);
}