package com.abrahambueno.orders.repository;

import com.abrahambueno.orders.model.Customer;
import com.abrahambueno.orders.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // something wrong
    @Query(value = "select * orders, from orders o, customers c where c.custname = :name ", nativeQuery = true)
    List<Order> findByCustName(@Param("name") String name);

    @Query(value = "select  c.custname, o.custcode from customers c, orders o where c.custcode = o.custcode order by c.custname", nativeQuery = true)
    List<Object[]> findAllOrders();

    // something is wrong
    @Query(value = "select o.ordnum, c.custname from orders o, customers c where c.custcode = o.custnum, c.custcode = :custcode", nativeQuery = true)
    List<Order> findOrdersByCustCode(@Param("custcode") Long custcode);


//    Customer findByCustCode(long custcode);
//    public Customer findByCustName(String name);
}